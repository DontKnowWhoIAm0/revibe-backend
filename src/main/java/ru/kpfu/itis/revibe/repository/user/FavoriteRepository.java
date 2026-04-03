package ru.kpfu.itis.revibe.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.revibe.entity.user.Favorite;
import ru.kpfu.itis.revibe.entity.user.User;
import ru.kpfu.itis.revibe.entity.products.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
    List<Favorite> findByUser(User user);
    Optional<Favorite> findByUserAndProduct(User user, Product product);
}