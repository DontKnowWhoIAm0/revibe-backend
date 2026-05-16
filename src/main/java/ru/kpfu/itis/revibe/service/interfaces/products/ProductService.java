package ru.kpfu.itis.revibe.service.interfaces.products;

import ru.kpfu.itis.revibe.dto.products.ProductCreateDto;
import ru.kpfu.itis.revibe.dto.products.ProductDetailDto;
import ru.kpfu.itis.revibe.entity.products.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDetailDto getProduct(UUID article);
    List<ProductDetailDto> getAllProducts();
    Product addProduct(ProductCreateDto dto);
    Product updateProduct(UUID article, ProductCreateDto dto);
    void deleteProduct(UUID article);
}
