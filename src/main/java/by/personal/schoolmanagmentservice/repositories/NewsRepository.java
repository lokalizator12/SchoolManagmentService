package by.personal.schoolmanagmentservice.repositories;

import by.personal.schoolmanagmentservice.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
