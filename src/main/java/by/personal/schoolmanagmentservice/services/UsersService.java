package by.personal.schoolmanagmentservice.services;

import by.personal.schoolmanagmentservice.entity.Image;
import by.personal.schoolmanagmentservice.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UsersService {

    List<User> getAllUsersWithParam(String roleName);

    void showImage(Long id, Optional<Image> images, HttpServletResponse response) throws IOException;

    void deleteOne(Long id);

}
