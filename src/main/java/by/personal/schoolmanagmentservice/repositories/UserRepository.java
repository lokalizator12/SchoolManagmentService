package by.personal.schoolmanagmentservice.repositories;

import by.personal.schoolmanagmentservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);

    boolean existsByLogin(String login);
}
