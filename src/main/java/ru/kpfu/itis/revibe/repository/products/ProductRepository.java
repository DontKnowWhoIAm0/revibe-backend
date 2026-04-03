package ru.kpfu.itis.revibe.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.revibe.entity.products.Product;
import ru.kpfu.itis.revibe.entity.products.Branch;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByBranch(Branch branch);
    List<Product> findByIsSoldFalse();
}