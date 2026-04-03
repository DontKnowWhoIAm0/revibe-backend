package ru.kpfu.itis.revibe.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.revibe.entity.enums.Role;
import ru.kpfu.itis.revibe.entity.user.User;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<User, UUID> {
    List<User> findByRole(Role role);
}
