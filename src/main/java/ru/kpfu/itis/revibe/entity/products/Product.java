package ru.kpfu.itis.revibe.entity.products;

import jakarta.persistence.*;
import ru.kpfu.itis.revibe.entity.enums.*;

import java.util.UUID;

@Entity
public class Product {

    @Id
    private UUID article;

    private String name;
    private String description;
    private int price;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Gender gender;
    private Color color;
    private Brand brand;
    private Size size;
    private Condition condition;
    private boolean isSold;

    private String imageUrl;

    @ManyToOne
    private Branch branch;

    public UUID  getArticle() {
        return article;
    }

    public void setArticle(UUID  article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        this.isSold = sold;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
