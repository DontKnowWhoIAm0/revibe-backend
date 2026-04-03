package ru.kpfu.itis.revibe.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.revibe.entity.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}