package pl.szvmczek.projecthuman.domain.task;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class TaskCompletion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id",referencedColumnName = "id",nullable = false)
    private Task task;

    public TaskCompletion(Task task, LocalDate date) {
        this.date = date;
        this.task = task;
    }

    public TaskCompletion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
