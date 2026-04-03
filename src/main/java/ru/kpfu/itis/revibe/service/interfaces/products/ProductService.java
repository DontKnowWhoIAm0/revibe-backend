package ru.kpfu.itis.revibe.service.interfaces.products;

import ru.kpfu.itis.revibe.dto.products.ProductCreateDto;
import ru.kpfu.itis.revibe.dto.products.ProductDetailDto;
import ru.kpfu.itis.revibe.entity.products.Product;

import java.util.List;

public interface ProductService {
    ProductDetailDto getProduct(String article);
    List<ProductDetailDto> getAllProducts();
    Product addProduct(ProductCreateDto dto);
    Product updateProduct(String article, ProductCreateDto dto);
    void deleteProduct(String article);
}
