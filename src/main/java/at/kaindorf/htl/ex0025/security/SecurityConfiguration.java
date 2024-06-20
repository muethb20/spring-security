package at.kaindorf.htl.ex0025.security;

import at.kaindorf.htl.ex0025.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Autowired
  private UserDetailService userDetailsService;

  @Autowired
  private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize ->
                    authorize.requestMatchers("/", "index").permitAll()
                            .requestMatchers("/resources").permitAll()
                            .requestMatchers("sad_yoda.gif").permitAll()
                            .requestMatchers("/admin").hasRole("ADMIN")
                            .requestMatchers("/user/all").hasRole("ADMIN")
                            .requestMatchers("/user/remove/**").hasRole("ADMIN")
                            .requestMatchers("/user/**").permitAll()
                            .requestMatchers("/todo/add").hasRole("ADMIN")
                            .requestMatchers("/todo/**").permitAll()
                            .anyRequest().denyAll()
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .successHandler(customAuthenticationSuccessHandler)
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .permitAll()
            );
    return http.build();
  }

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
