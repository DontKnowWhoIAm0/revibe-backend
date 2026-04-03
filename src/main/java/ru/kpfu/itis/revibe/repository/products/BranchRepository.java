package ru.kpfu.itis.revibe.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.revibe.entity.products.Branch;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {

    Optional<Branch> findByName(String name);
    List<Branch> findByCity(String city);
    List<Branch> findByAddressContaining(String keyword);

}