package com.syberry.school.services;

import com.syberry.school.entity.Image;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface ImageService {
    void showImage(Long id, Optional<Image> image,
                   HttpServletResponse response) throws IOException;

    List<Image> getAllImages();

    String showImageDetails(Long id, Optional<Image> image, Model model);

    void saveImage(List<MultipartFile> files, String name, String description) throws IOException;

    void deleteOne(Long id);
}
