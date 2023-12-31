package by.personal.schoolmanagmentservice.dto;


import by.personal.schoolmanagmentservice.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RoleDto {

    private String name;

    public static RoleDto toDto(Role entity) {
        RoleDto dto = new RoleDto();
        dto.setName(entity.getName());
        return dto;
    }
}
