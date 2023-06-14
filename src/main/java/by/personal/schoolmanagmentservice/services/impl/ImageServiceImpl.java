package by.personal.schoolmanagmentservice.services.impl;

import by.personal.schoolmanagmentservice.entity.Image;
import by.personal.schoolmanagmentservice.exceptions.ImageNotFoundException;
import by.personal.schoolmanagmentservice.repositories.ImageRepository;
import by.personal.schoolmanagmentservice.services.ImageService;
import by.personal.schoolmanagmentservice.utils.UploadImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static by.personal.schoolmanagmentservice.utils.ApplicationConstants.UPLOAD_DIRECTORY_GALLERY;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public void showImage(Long id, Optional<Image> image, HttpServletResponse response) throws IOException {
        image = imageRepository.findById(id);
        response.setContentType("image/jpeg, image/png, image/jpg, image/gif");
        response.getOutputStream().write(image.get().getImage());
        response.getOutputStream().close();
    }

    @Override
    public String showImageDetails(Long id, Optional<Image> image, Model model) {
        if (id != 0) {
            image = imageRepository.findById(id);
            if (image.isPresent()) {
                model.addAttribute("id", image.get().getId());
                model.addAttribute("description", image.get().getDescription());
                model.addAttribute("name", image.get().getName());
                return "imagedetails";
            }
            return "redirect:/uploadImage";
        }
        return "redirect:/uploadImage";
    }

    @Override
    public void saveImage(List<MultipartFile> files, String name, String description) throws IOException {
        UploadImage uploadImage = new UploadImage();
        for (MultipartFile file : files) {
            uploadImage.upload(file, UPLOAD_DIRECTORY_GALLERY);
            imageRepository.save(Image.builder()
                    .image(file.getBytes())
                    .createDate(new Date())
                    .name(name)
                    .description(description).build());
        }
    }

    @Override
    public void deleteOne(Long id) {
        if (!imageRepository.existsById(id)) {
            throw new ImageNotFoundException();
        } else {
            imageRepository.deleteById(id);
        }
    }
}
