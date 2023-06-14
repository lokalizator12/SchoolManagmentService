package by.personal.schoolmanagmentservice.services;

import by.personal.schoolmanagmentservice.entity.News;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface NewsService {

    List<News> getNews();

    Optional<News> getAllNewsById(Long id);

    Optional<News> getOneNews(Long id);

    List<News> getAllNews();

    void createNews(String header, String description, MultipartFile reviewFile, List<MultipartFile> file, String author) throws IOException;

    void deleteOnePost(Long id_post);
}
