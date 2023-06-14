package com.syberry.school.services.impl;

import com.syberry.school.entity.Image;
import com.syberry.school.entity.User;
import com.syberry.school.repositories.UserRepository;
import com.syberry.school.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsersWithParam(String roleName) {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            log.info("User is not found");
            throw new UsernameNotFoundException("User is not found");
        }
        List<User> students = new ArrayList<>();
        users.forEach(user -> user.getRoles().stream()
                .filter(role -> role.getName().equals(roleName))
                .map(role -> user)
                .forEachOrdered(students::add));
        return students;
    }

    @Override
    public void showImage(Long id, Optional<Image> images, HttpServletResponse response) throws IOException {
        Optional<User> user = userRepository.findById(id);
        response.setContentType("image/jpeg, image/png, image/jpg, image/gif");
        response.getOutputStream().write(user.get().getAvatar());
        response.getOutputStream().close();
    }

    @Override
    public void deleteOne(Long id) {
        log.info("Searching user with id: {} in database", id);
        userRepository.deleteById(id);
    }
}
