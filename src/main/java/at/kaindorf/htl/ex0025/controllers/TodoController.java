package at.kaindorf.htl.ex0025.controllers;

import at.kaindorf.htl.ex0025.dto.TodoDto;
import at.kaindorf.htl.ex0025.dto.UserDto;
import at.kaindorf.htl.ex0025.services.TodoService;
import at.kaindorf.htl.ex0025.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("todo")
public class TodoController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private TodoService todoService;
    private UserService userService;

    @Autowired
    public TodoController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    /*@GetMapping("all")
    public String showAll(Model model) {
        model.addAttribute("todos", todoService.findAllTodos());
        return "todos";
    }*/

    @GetMapping("remove")
    public String deleteUser(@RequestParam("todoId") int todoId) {

        logger.info("--> deleteUser() !!" + todoId);
        todoService.deleteTodo(todoId);

        return "redirect:/todo/all";
    }

    @GetMapping("add")
    public String showAddTodoForm(Model model) {
        logger.info("---> addTodoForm()");
        TodoDto todo = new TodoDto();
        model.addAttribute("todo", todo);
        model.addAttribute("users", userService.findAllUsers());
        return "addTodo";
    }

    @PostMapping("addTodo")
    public String addTodo(@Valid @ModelAttribute("todo") TodoDto todoDto) {
        todoService.saveTodo(todoDto);
        logger.info("--> addTodo() !!" + todoDto);
        return "redirect:/todo/add";
    }

    @GetMapping("all")
    public String showAll(Model model, Principal principal) {
        model.addAttribute("todos", todoService.findTodoByUsername(principal.getName()));
        logger.info("-- " + principal.getName());
        return "todos";
    }
}
