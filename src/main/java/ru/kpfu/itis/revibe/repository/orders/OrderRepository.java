package ru.kpfu.itis.revibe.repository.orders;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.revibe.entity.orders.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUserId(UUID userId);
    List<Order> findByBranchId(UUID branchId);
    List<Order> findByIdOrBranchId(UUID orderId, UUID branchId);
}

