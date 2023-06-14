package by.personal.schoolmanagmentservice.controllers;

import by.personal.schoolmanagmentservice.entity.Image;
import by.personal.schoolmanagmentservice.repositories.ImageRepository;
import by.personal.schoolmanagmentservice.services.ImageService;
import by.personal.schoolmanagmentservice.validations.FileIsExists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/image/")
public class ImageController {

    private final ImageService imageService;
    private final ImageRepository imageRepository;

    @RequestMapping("/delete/{id}")
    public String deleteImage(@PathVariable("id") Long id) {
        imageService.deleteOne(id);
        return "redirect:/image/show";
    }

    @GetMapping(value = {"uploadImage"})
    public String showUploadPage() {
        return "uploadimage";
    }

    @PostMapping("/saveImageDetails")
    public String uploadImageGallery(@RequestParam("name") String nameImage,
                                     @RequestParam("description") String description,
                                     @RequestParam("image") @FileIsExists List<MultipartFile> files) throws IOException {
        log.info("Request to upload image");
        imageService.saveImage(files, nameImage, description);
        log.info("Image saved");
        return "uploadimage";
    }

    @GetMapping("/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Image> images) throws IOException {
        log.info("Showing image by id:" + id);
        imageService.showImage(id, images, response);
        log.info("Showed image");
    }

    @GetMapping("/imageDetails")
    public String showDetails(@RequestParam("id") Long id, Optional<Image> image, Model model) {
        try {
            log.info("Show details from ID:" + id);
            return imageService.showImageDetails(id, image, model);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/image/uploadImage";
        }
    }

    @GetMapping("/show")
    private String showImages(Model map) {
        log.info("Show images");
        List<Image> images = imageRepository.findAll();
        map.addAttribute("gallery", images);
        return "gallery";
    }
}
