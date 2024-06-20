package at.kaindorf.htl.ex0025.repositories;

import at.kaindorf.htl.ex0025.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
  public User findByName(String name);

  public User removeMyUserByName(String name);
}
