package pl.szvmczek.projecthuman.domain.habit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class HabitUpdateDto {
    @Positive(message = "Dont try to be sneaky! You won't crack it")
    private Long id;
    @NotBlank(message = "Title cannot be empty")
    @Size(max = 50,message = "Title must be less than 50 characters long")
    private String title;
    @Size(max = 100,message = "Description must be less than 100 characters long")
    private String description;
    @Positive(message = "Category id must be positive")
    private Long categoryId;

    public HabitUpdateDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
