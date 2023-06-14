package by.personal.schoolmanagmentservice.controllers;

import by.personal.schoolmanagmentservice.entity.User;
import by.personal.schoolmanagmentservice.repositories.UserRepository;
import by.personal.schoolmanagmentservice.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserRepository userRepository;
    private final AuthorizationService authorizationService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/login")
    private String login() {
        log.info("Getting login html-page");
        return "login";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/registration")
    private String registration(Model model) {
        log.info("Create nulled user");
        model.addAttribute("user", new User());
        log.info("Getting registration html-page");
        return "registration";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/registration")
    private String register(@Valid @ModelAttribute User user, BindingResult result) throws IOException {
        log.info("Start registration profile with id:{}", user.getId());
        if (result.hasErrors()) {
            return "registration";
        }
        if (userRepository.existsByLogin(user.getLogin())) {
            return "registration";
        }
        authorizationService.save(user);
        log.info("Registration profile with id:{} finished successful", user.getId());
        log.info("Redirect to login page");
        return "login";
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    private String signUp() {
        return "redirect:/";
    }
}
