package by.personal.schoolmanagmentservice.repositories;

import by.personal.schoolmanagmentservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
