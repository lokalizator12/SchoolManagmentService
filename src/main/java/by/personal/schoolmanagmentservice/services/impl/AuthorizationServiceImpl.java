package by.personal.schoolmanagmentservice.services.impl;

import by.personal.schoolmanagmentservice.entity.User;
import by.personal.schoolmanagmentservice.repositories.UserRepository;
import by.personal.schoolmanagmentservice.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public User save(User user) throws IOException {
        log.info("Hashing password for user with id:{}", user.getId());
        user.setAvatar(Files.readAllBytes(Paths.get("src/main/resources/static/images/avatars", "avatar-null.jpg")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving user with id:{}", user.getId());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        log.info("Request to database if have user with login:{}", login);
        return userRepository.findOneByLogin(login);
    }
}
