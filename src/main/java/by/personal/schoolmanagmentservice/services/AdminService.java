package by.personal.schoolmanagmentservice.services;

import by.personal.schoolmanagmentservice.entity.Role;
import by.personal.schoolmanagmentservice.entity.User;

import java.util.List;
import java.util.Optional;


public interface AdminService {

    void update(User user);

    List<User> getAllUsers();

    void deleteUser(Long id);

    Optional<User> getUser(Long id);

    List<Role> getRoles();
}
