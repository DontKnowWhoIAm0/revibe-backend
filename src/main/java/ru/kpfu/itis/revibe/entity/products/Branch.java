package ru.kpfu.itis.revibe.entity.products;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Branch {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String city;
    private String address;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private List<Product> products;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
