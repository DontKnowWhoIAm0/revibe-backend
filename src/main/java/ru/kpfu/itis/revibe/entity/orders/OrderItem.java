package ru.kpfu.itis.revibe.entity.orders;

import jakarta.persistence.*;
import ru.kpfu.itis.revibe.entity.products.Product;

import java.util.UUID;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

