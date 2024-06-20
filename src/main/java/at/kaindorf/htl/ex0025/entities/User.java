package at.kaindorf.htl.ex0025.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="myusers")
public class User
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column(nullable = false)
  private String password;

  private int yearOfBirth;

  @ToString.Exclude
  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private List<Todo> todoList = new ArrayList<Todo>();

  @ElementCollection(fetch = FetchType.EAGER)
  private Set<String> roles = new HashSet<>();


}
