package by.personal.schoolmanagmentservice.services.impl;

import by.personal.schoolmanagmentservice.entity.User;
import by.personal.schoolmanagmentservice.repositories.RoleRepository;
import by.personal.schoolmanagmentservice.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("AdminServiceImpl test")
class AdminServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    @DisplayName("Should return all users in database")
    void getAllUsersShouldReturnAllUsersInDatabase() {
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));
        assertEquals(2, adminService.getAllUsers().size());
    }

    @Test
    @DisplayName("Should throw an exception when the user is null")
    void saveWhenUserIsNullThenThrowException() {
        assertThrows(NullPointerException.class, () -> adminService.update(null));
    }

    @Test
    @DisplayName("Should return all roles from database")
    void getRolesShouldReturnAllRolesFromDatabase() {
        adminService.getRoles();
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return empty when the user does not exist")
    void getUserWhenUserDoesNotExist() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<User> user = adminService.getUser(1L);
        assertTrue(user.isEmpty());
    }

    @Test
    @DisplayName("Should return the user when the user exists")
    void getUserWhenUserExists() {
        User user =
                User.builder()
                        .id(1L)
                        .login("login")
                        .firstName("firstName")
                        .lastName("lastName")
                        .email("email")
                        .password("password")
                        .build();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        Optional<User> actualUser = adminService.getUser(1L);
        assertEquals(user, actualUser.get());
    }
}