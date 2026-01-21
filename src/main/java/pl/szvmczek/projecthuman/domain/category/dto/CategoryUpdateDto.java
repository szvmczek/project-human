package pl.szvmczek.projecthuman.domain.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CategoryUpdateDto {
    @Positive(message = "Category Id must be a positive number")
    private Long id;
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 50,message = "Name must be less than 50 characters long")
    private String name;

    public CategoryUpdateDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
