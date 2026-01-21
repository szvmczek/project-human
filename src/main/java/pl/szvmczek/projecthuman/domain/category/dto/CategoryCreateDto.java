package pl.szvmczek.projecthuman.domain.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryCreateDto {
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 50,message = "Name must be less than 50 characters long")
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
