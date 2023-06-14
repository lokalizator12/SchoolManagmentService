package by.personal.schoolmanagmentservice.controllers;

import by.personal.schoolmanagmentservice.entity.User;
import by.personal.schoolmanagmentservice.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    private String getUsers(Model model) {
        log.info("Getting users in admin-page");
        model.addAttribute("users", adminService.getAllUsers());
        return "admin";
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/delete/{id}")
    private String deleteUser(@PathVariable("id") Long userId) {
        log.info("Start deleting user with id: {}", userId);
        adminService.deleteUser(userId);
        log.info("User with id: {} deleted.", userId);
        return "admin";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long userId, Model model) {
        log.info("Getting html update page");
        User user = adminService.getUser(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + userId));
        log.info("Add user with id:{} to model", user.getId());
        model.addAttribute("user", user);
        log.info("Getting and add roles for update-panel");
        model.addAttribute("roles", adminService.getRoles());
        return "admin-update";
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/update/{id}")
    private String updateUser(@PathVariable("id") Long userId, User user, Model model) {
        adminService.update(user);
        model.addAttribute("users", adminService.getAllUsers());
        return "admin";
    }
}
