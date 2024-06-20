package at.kaindorf.htl.ex0025.controllers;

import at.kaindorf.htl.ex0025.entities.User;
import at.kaindorf.htl.ex0025.dto.UserDto;
import at.kaindorf.htl.ex0025.services.TodoService;
import at.kaindorf.htl.ex0025.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController
{
  private Logger logger = LoggerFactory.getLogger(getClass());

  private UserService userService;
  private TodoService todoService;

  @Autowired
  public UserController(UserService userService, TodoService todoService)
  {
    this.userService = userService;
    this.todoService = todoService;
  }

  @GetMapping("register")
  public String showRegistrationForm(Model model)
  {
    logger.info("--> showRegistrationForm() !!");
    UserDto user = new UserDto();
    model.addAttribute("user",user);
    return "register";
  }

  @PostMapping("register/save")
  public String registration(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model)
  {
    logger.info("--> registration() !!");
    logger.info(" --> " + user);

    User existingUser = userService.findByName( user.getName() );
    logger.info("--> " + existingUser);

    if(existingUser != null && existingUser.getName() != null && ! existingUser.getName().isEmpty())
    {
      result.rejectValue("name",null,
              "There is already an account registered with that name");
    }
    if(result.hasErrors())
    {
      logger.info("--> ERROR !!!");
      model.addAttribute("user", user);
      return "register";
    }

    userService.saveUser(user);
    return "redirect:/user/register?success";
  }

  @GetMapping("remove")
  public String deleteUser(@RequestParam("name") String username) {
    logger.info("--> deleteUser() !!" + username);

    todoService.deleteTodoByUsername(username);
    userService.removeUserByName(username);

    return "redirect:/user/all";
  }

  @GetMapping("all")
  public String listRegisteredUsers(Model model)
  {
    model.addAttribute("users",userService.findAllUsers());
    return "users";
  }

}
