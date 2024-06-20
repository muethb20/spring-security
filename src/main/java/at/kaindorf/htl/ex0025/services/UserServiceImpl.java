package at.kaindorf.htl.ex0025.services;

import at.kaindorf.htl.ex0025.entities.User;
import at.kaindorf.htl.ex0025.entities.Todo;
import at.kaindorf.htl.ex0025.dto.UserDto;
import at.kaindorf.htl.ex0025.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
  private UserRepository userRepository;

  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder)
  {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void saveUser(UserDto userDto)
  {
    User user = new User();
    user.setName(userDto.getName());
    user.setYearOfBirth(userDto.getYearOfBirth());
    user.setPassword( passwordEncoder.encode(userDto.getPassword()) );
    user.getRoles().add("ROLE_USER");
    user.getTodoList().add(new Todo(1,"Welcome", "Welcome to the task world", user));
    userRepository.save(user);
  }

  @Override
  public User findByName(String name)
  {
    return userRepository.findByName(name);
  }

  @Override
  public List<UserDto> findAllUsers()
  {
    List<User> users = userRepository.findAll();
    return users.stream().map( user ->
    {
      UserDto userDto = new UserDto();
      userDto.setName(user.getName());
      userDto.setYearOfBirth(user.getYearOfBirth());
      return userDto;
    }).collect(Collectors.toList());
  }

  @Override
  public User removeUserByName(String userName) {
    User user = userRepository.findByName(userName);
    userRepository.delete(user);
    return user;
  }
}
