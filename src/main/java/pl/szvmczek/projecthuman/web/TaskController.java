package pl.szvmczek.projecthuman.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.szvmczek.projecthuman.domain.task.Task;
import pl.szvmczek.projecthuman.domain.task.TaskService;
import pl.szvmczek.projecthuman.domain.user.User;
import pl.szvmczek.projecthuman.domain.user.UserService;
import pl.szvmczek.projecthuman.domain.user.dto.UserCredentialsDto;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService,UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserCredentialsDto user){
        if(user == null){
            return "redirect:/login";
        }
        Long userId = user.getId();
        List<Task> tasks = taskService.findAllTasksFromUserId(userId);
        model.addAttribute("tasks",tasks);
        return "index";
    }

    @GetMapping("/add")
    public String viewAddForm(Model model){
        model.addAttribute("task",new Task());
        return "add-form";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task){
        taskService.saveTask(task);
        return "redirect:/";
    }

    @PostMapping("/complete")
    public String completeTask(@RequestParam Long id){
        Optional<Task> task = taskService.findTaskById(id);
        task.ifPresent(t -> taskService.changeStatus(id));
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteTask(@RequestParam Long id){
        taskService.deleteTask(id);
        return "redirect:/";
    }
}
