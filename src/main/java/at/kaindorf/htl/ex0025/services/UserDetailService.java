package at.kaindorf.htl.ex0025.services;

import at.kaindorf.htl.ex0025.entities.User;
import at.kaindorf.htl.ex0025.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService
{
  private UserRepository myUserRepository;

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired
  public UserDetailService(UserRepository myUserRepository)
  {
    this.myUserRepository = myUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    User user = myUserRepository.findByName(username);
    logger.info("--> "+user);
    if(user != null)
    {
      return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
    else
    {
      throw new UsernameNotFoundException("--> Invalid username !!!");
    }
  }
}
