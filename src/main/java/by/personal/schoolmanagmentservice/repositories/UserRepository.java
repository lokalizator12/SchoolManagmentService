package com.syberry.school.repositories;

import com.syberry.school.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByLogin(String login);

    boolean existsByLogin(String login);
}
