package pl.szvmczek.projecthuman.domain.badhabit;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import pl.szvmczek.projecthuman.domain.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class BadHabit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50,nullable = false)
    private String title;

    @Column(length = 100)
    private String description;

    @Column(nullable = false,updatable = false)
    private LocalDate createDate;

    @Column(nullable = true)
    private LocalDateTime startDateTime = null;

    @Column(nullable = false)
    private boolean active = false;

    @PositiveOrZero
    private int resetCount = 0;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    private User user;

    public BadHabit(String title, String description) {
        if(title == null || title.isBlank()) throw new IllegalArgumentException("Title cannot be empty");
        this.title = title.trim();
        this.description = description.trim();
        this.createDate = LocalDate.now();
    }

    public BadHabit() {
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
        this.title = title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public LocalDate getCreateDate() {
        return createDate;
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
