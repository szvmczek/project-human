package pl.szvmczek.projecthuman.domain.task;

import jakarta.persistence.EntityManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szvmczek.projecthuman.domain.task.dto.TaskEditDto;
import pl.szvmczek.projecthuman.domain.user.dto.UserCredentialsDto;
import pl.szvmczek.projecthuman.error.AuthorizationDeniedException;
import pl.szvmczek.projecthuman.error.TaskNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void changeStatus(Long id){
        Task task = taskRepository.findById(id).get();
        task.setDone(!task.isDone());
    }

    public List<Task> findAllTasksFromUserId(Long userId){
        return taskRepository.findAllByUserId(userId);
    }

    @Transactional
    public void updateTask(TaskEditDto dto, Long userId){
        Task originalTask = taskRepository.findByIdAndUserId(dto.getId(), userId)
                .orElseThrow(() -> new AccessDeniedException("Task not found or wrong authentication!"));
        originalTask.setTitle(dto.getTitle());
        originalTask.setDescription(dto.getDescription());
    }

    public void saveTask(Task task){
        taskRepository.save(task);
    }

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public Optional<Task> findTaskById(Long id){
        return taskRepository.findById(id);
    }
}
