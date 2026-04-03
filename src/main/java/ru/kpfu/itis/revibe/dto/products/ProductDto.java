package ru.kpfu.itis.revibe.dto.products;

import ru.kpfu.itis.revibe.entity.enums.Brand;
import ru.kpfu.itis.revibe.entity.enums.Color;
import ru.kpfu.itis.revibe.entity.enums.Gender;
import ru.kpfu.itis.revibe.entity.enums.Size;

import java.util.UUID;

public class ProductDto {
    private UUID article;
    private String name;
    private int price;
    private String imageUrl;
    private Gender gender;
    private Color color;
    private Brand brand;
    private Size size;

    public UUID getArticle() {
        return article;
    }

    public void setArticle(UUID article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}