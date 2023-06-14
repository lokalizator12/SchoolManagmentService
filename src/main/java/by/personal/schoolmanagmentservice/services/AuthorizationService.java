package com.syberry.school.services;

import com.syberry.school.entity.User;

import java.io.IOException;
import java.util.Optional;

public interface AuthorizationService {

    User save(User user) throws IOException;

    Optional<User> findByLogin(String login);
}
