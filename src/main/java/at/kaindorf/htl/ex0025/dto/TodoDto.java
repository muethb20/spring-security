package at.kaindorf.htl.ex0025.dto;

import at.kaindorf.htl.ex0025.entities.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TodoDto {

    @NotEmpty(message="Title should not be empty")
    private String title;
    @NotEmpty(message="Description should not be empty")
    private String description;
    private String username;
}
