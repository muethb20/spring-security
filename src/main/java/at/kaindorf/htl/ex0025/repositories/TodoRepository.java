package at.kaindorf.htl.ex0025.repositories;

import at.kaindorf.htl.ex0025.entities.Todo;
import at.kaindorf.htl.ex0025.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    public Todo findTodoById(int id);
    public List<Todo> findTodoByUser(User user);
    public List<Todo> deleteTodoByUser(User user);
}
