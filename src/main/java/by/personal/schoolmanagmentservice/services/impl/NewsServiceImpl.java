package by.personal.schoolmanagmentservice.services.impl;

import by.personal.schoolmanagmentservice.entity.News;
import by.personal.schoolmanagmentservice.repositories.NewsRepository;
import by.personal.schoolmanagmentservice.repositories.UserRepository;
import by.personal.schoolmanagmentservice.services.NewsService;
import by.personal.schoolmanagmentservice.utils.UploadImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static by.personal.schoolmanagmentservice.utils.ApplicationConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    @Override
    public void createNews(String header, String description, MultipartFile previewFile, List<MultipartFile> images, String authorName) throws IOException {
        log.info("Start creating news");
        log.info("Uploading preview image with name:{}", previewFile.getOriginalFilename());
        UploadImage uploadImage = new UploadImage();
        uploadImage.upload(previewFile, UPLOAD_DIRECTORY_PREVIEW);
        for (MultipartFile file : images) {
            uploadImage.upload(file, UPLOAD_DIRECTORY_IMAGES);
            log.info("Uploading image with name:{}", file.getOriginalFilename());
            newsRepository.save(News.builder()
                    .date(DATE_FORMAT_ENTITY.format(Timestamp.valueOf(LocalDateTime.now())))
                    .image(file.getBytes())
                    .imageName(previewFile.getOriginalFilename())
                    .previewImage(previewFile.getBytes())
                    .description(description)
                    .user(userRepository.findOneByLogin(authorName).get())
                    .header(header).build());

        }
        log.info(LOG_CREAT_POST_SUCCESSFUL, header);
    }

    @Override
    public void deleteOnePost(Long id_post) {
        newsRepository.deleteById(id_post);
    }

    @Override
    public Optional<News> getOneNews(Long id) {
        log.info("Request to get post with news with id:{}", id);
        return newsRepository.findById(id);
    }

    @Override
    public Optional<News> getAllNewsById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public List<News> getAllNews() {
        log.info("getting news");
        return newsRepository.findAll();
    }

    @Override
    public List<News> getNews() {
        return newsRepository.findAll();
    }
}
