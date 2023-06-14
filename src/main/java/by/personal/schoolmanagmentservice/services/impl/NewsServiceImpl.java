package com.syberry.school.services.impl;

import com.syberry.school.entity.News;
import com.syberry.school.repositories.NewsRepository;
import com.syberry.school.repositories.UserRepository;
import com.syberry.school.services.NewsService;
import com.syberry.school.utils.UploadImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.syberry.school.utils.ApplicationConstants.DATE_FORMAT_ENTITY;
import static com.syberry.school.utils.ApplicationConstants.LOG_CREAT_POST_SUCCESSFUL;
import static com.syberry.school.utils.ApplicationConstants.UPLOAD_DIRECTORY_IMAGES;
import static com.syberry.school.utils.ApplicationConstants.UPLOAD_DIRECTORY_PREVIEW;

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
