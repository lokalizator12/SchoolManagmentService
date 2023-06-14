package by.personal.schoolmanagmentservice.services;

import by.personal.schoolmanagmentservice.entity.User;

import java.io.IOException;
import java.util.Optional;

public interface AuthorizationService {

    User save(User user) throws IOException;

    Optional<User> findByLogin(String login);
}
