package by.personal.schoolmanagmentservice.controllers;

import by.personal.schoolmanagmentservice.services.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping()
public class NewsController {

    private final NewsService newsService;

    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    @GetMapping("/news/create")
    private String showCreateNewsPage() {
        return "createNews";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping({"/home", "/"})
    public String home(Model model) {
        log.info("Request to get home page with news");
        model.addAttribute("listNews", newsService.getAllNews());
        return "index";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_TEACHER')")
    @PostMapping("/news/create")
    public String createNews(@RequestParam("header") String header,
                             @RequestParam("description") String description,
                             final @RequestParam("previewImage") MultipartFile reviewFile,
                             final @RequestParam("image") List<MultipartFile> files, Principal principal) throws IOException {
        log.info("Request to create new post");
        newsService.createNews(header, description, reviewFile, files, principal.getName());
        log.info("Post created");
        return "createNews";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("/news/delete/{id}")
    public String deleteOnePost(@PathVariable Long id) {
        log.info("Delete post with id : {}", id);
        newsService.deleteOnePost(id);
        log.info("Deleting post is accepted");
        return "redirect:/home";
    }
}
