package at.kaindorf.htl.ex0025.services;

import at.kaindorf.htl.ex0025.entities.Todo;
import at.kaindorf.htl.ex0025.dto.TodoDto;

import java.util.List;

public interface TodoService {
    void saveTodo(TodoDto todo);

    Todo findById(int id);

    List<Todo> findAllTodos();

    Todo deleteTodo(int id);

    List<Todo> findTodoByUsername(String username);

    List<Todo> deleteTodoByUsername(String username);
}
