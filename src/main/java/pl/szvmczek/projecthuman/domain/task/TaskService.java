package pl.szvmczek.projecthuman.domain.task;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    void changeStatus(Long id){
        Task task = taskRepository.findById(id).get();
        task.setDone(!task.isDone());
        taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public void saveTask(Task task){
        taskRepository.save(task);
    }
}
