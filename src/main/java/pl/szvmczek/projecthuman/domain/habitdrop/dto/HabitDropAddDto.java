package pl.szvmczek.projecthuman.domain.habitdrop.dto;

public class HabitDropAddDto {
    private String title;
    private String description;

    public HabitDropAddDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public HabitDropAddDto() {
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
}
