package by.personal.schoolmanagmentservice.services.impl;

import by.personal.schoolmanagmentservice.entity.Role;
import by.personal.schoolmanagmentservice.entity.User;
import by.personal.schoolmanagmentservice.repositories.RoleRepository;
import by.personal.schoolmanagmentservice.repositories.UserRepository;
import by.personal.schoolmanagmentservice.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Optional<User> getUser(Long userId) {
        log.info("Getting user with id: {} from database.", userId);
        return userRepository.findById(userId);
    }

    @Override
    public List<Role> getRoles() {
        log.info("Getting roles from database");
        return roleRepository.findAll();
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Delete user with id: {}", userId);
        userRepository.deleteById(userId);
    }

    @Override
    public void update(User user) {
        log.info("Starting update user with id: {}", user.getId());
        Optional<User> beforeUser = userRepository.findById(user.getId());
        user.setAvatar(beforeUser.get().getAvatar());
        user.setPassword(beforeUser.get().getPassword());
        beforeUser.get().setRoles(user.getRoles());
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("Getting all users in database");
        return userRepository.findAll();
    }
}
