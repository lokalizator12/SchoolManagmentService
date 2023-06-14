package by.personal.schoolmanagmentservice.dto;

import by.personal.schoolmanagmentservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAdminDto {

    private Long id;
    private String FirstName;
    private String LastName;
    private String login;
    private String email;
    private String rolesList;

    public static UserAdminDto toDto(User entity) {
        UserAdminDto dto = new UserAdminDto();
        dto.setEmail(entity.getEmail());
        dto.setId(entity.getId());
        dto.setLogin(entity.getLogin());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setRolesList(entity.getRoles().stream().map(String::valueOf).collect(Collectors.joining(",")));
        return dto;
    }

}
