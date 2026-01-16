package pl.szvmczek.projecthuman.domain.habitdrop;

import jakarta.persistence.*;
import pl.szvmczek.projecthuman.domain.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class HabitDrop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate createDate;
    private LocalDateTime startDateTime;
    private boolean active = false;
    private int resetCount = 0;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public HabitDrop(String title, String description) {
        this.title = title;
        this.description = description;
        this.createDate = LocalDate.now();
    }

    public HabitDrop() {
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getResetCount() {
        return resetCount;
    }

    public void setResetCount(int resetCount) {
        this.resetCount = resetCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
