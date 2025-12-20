package pl.szvmczek.projecthuman.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.szvmczek.projecthuman.domain.task.Task;
import pl.szvmczek.projecthuman.domain.task.TaskService;

import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<Task> allTasks = taskService.getAllTasks();
        model.addAttribute("tasks",allTasks);
        return "index";
    }
}
