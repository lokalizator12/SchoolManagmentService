package by.personal.schoolmanagmentservice.controllers;

import by.personal.schoolmanagmentservice.entity.Image;
import by.personal.schoolmanagmentservice.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/users/")
@RequiredArgsConstructor
class UsersController {

    private final UsersService usersService;

    @RequestMapping("/delete/{id}")
    public String deleteImage(@PathVariable("id") Long id) {
        usersService.deleteOne(id);
        return "redirect:/users/students";
    }

    @GetMapping("/avatar/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Image> images) throws IOException {
        log.info("Showing image by id:" + id);
        usersService.showImage(id, images, response);
        log.info("Showed image");
    }

    @GetMapping("students")
    private String showStudents(Model map) {
        log.info("Show students");
        map.addAttribute("students", usersService.getAllUsersWithParam("ROLE_STUDENT"));
        return "students";
    }

    @GetMapping("teachers")
    private String showTeachers(Model map) {
        log.info("Show teachers");
        map.addAttribute("teachers", usersService.getAllUsersWithParam("ROLE_TEACHER"));
        return "teachers";
    }
}
