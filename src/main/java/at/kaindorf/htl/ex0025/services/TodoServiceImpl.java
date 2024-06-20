package at.kaindorf.htl.ex0025.services;

import at.kaindorf.htl.ex0025.entities.Todo;
import at.kaindorf.htl.ex0025.dto.TodoDto;
import at.kaindorf.htl.ex0025.entities.User;
import at.kaindorf.htl.ex0025.repositories.TodoRepository;
import at.kaindorf.htl.ex0025.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private UserRepository userRepository;

    public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveTodo(TodoDto todoDto) {
        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setUser(userRepository.findByName(todoDto.getUsername()));
        todoRepository.save(todo);
    }

    @Override
    public Todo findById(int id) {
        return todoRepository.findTodoById(id);
    }

    @Override
    public List<Todo> findAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo deleteTodo(int id) {
        Todo todo = todoRepository.findTodoById(id);
        todoRepository.delete(todo);
        return todo;
    }

    @Override
    public List<Todo> findTodoByUsername(String username) {
        return todoRepository.findTodoByUser(userRepository.findByName(username));
    }

    @Override
    @Transactional
    public List<Todo> deleteTodoByUsername(String username) {
        return todoRepository.deleteTodoByUser(userRepository.findByName(username));
    }
}
