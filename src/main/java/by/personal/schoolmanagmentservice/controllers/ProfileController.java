package by.personal.schoolmanagmentservice.controllers;


import by.personal.schoolmanagmentservice.entity.User;
import by.personal.schoolmanagmentservice.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {


    private final AdminService adminService;

    @GetMapping("/{profileId}")
    private String getProfilePage(@PathVariable Long profileId, Model model) {
        User user = adminService.getUser(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + profileId));
        model.addAttribute("user", user);
        return "profile";
    }


    @PostMapping("/confirm-email")
    public String confirmEmail(@RequestParam("email") String email, Model model) {


        model.addAttribute("email", email);
        return "confirmation";
    }
}
