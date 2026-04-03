package ru.kpfu.itis.revibe.entity.user;

import jakarta.persistence.*;
import ru.kpfu.itis.revibe.entity.products.Product;

import java.util.UUID;

@Entity
public class Favorite {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}