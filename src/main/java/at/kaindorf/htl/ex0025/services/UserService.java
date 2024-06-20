package at.kaindorf.htl.ex0025.services;

import at.kaindorf.htl.ex0025.entities.User;
import at.kaindorf.htl.ex0025.dto.UserDto;

import java.util.List;

public interface UserService
{
  void saveUser(UserDto userDto);

  User findByName(String name);

  List<UserDto> findAllUsers();

  User removeUserByName(String userName);

}
