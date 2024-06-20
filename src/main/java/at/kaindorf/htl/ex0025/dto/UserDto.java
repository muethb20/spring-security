package at.kaindorf.htl.ex0025.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

// Dto ... data transfer object
// transfer the data between the controller layer and the view layer

@Data
public class UserDto
{

  @NotEmpty(message="Name should not be empty")
  private String name;

  @NotEmpty(message = "Password should not be empty")
  private String password;

  private int yearOfBirth;


}
