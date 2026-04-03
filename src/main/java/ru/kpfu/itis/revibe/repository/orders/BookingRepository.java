package ru.kpfu.itis.revibe.repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.revibe.entity.orders.Booking;
import ru.kpfu.itis.revibe.entity.user.User;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByUser(User user);
    boolean existsByProductArticleAndCompletedFalse(UUID productId);
}
