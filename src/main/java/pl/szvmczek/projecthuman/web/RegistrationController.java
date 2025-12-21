package pl.szvmczek.projecthuman.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.szvmczek.projecthuman.domain.user.UserService;
import pl.szvmczek.projecthuman.domain.user.dto.UserRegistrationDto;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registrationPage(Model model){
        model.addAttribute("user",new UserRegistrationDto());
        return "registration-form";
    }

    @PostMapping
    public String registerUser(@ModelAttribute UserRegistrationDto registration){
        userService.registerUserWithDefaultRole(registration);
        return "redirect:/login";
    }
}
