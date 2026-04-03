package ru.kpfu.itis.revibe.controller.client;

import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.revibe.dto.products.ProductDetailDto;
import ru.kpfu.itis.revibe.dto.products.ProductCreateDto;
import ru.kpfu.itis.revibe.service.interfaces.products.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) { this.productService = productService; }

    @GetMapping
    public List<ProductDetailDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDetailDto getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public void addProduct(@RequestBody ProductCreateDto dto) {
        productService.addProduct(dto);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable String id, @RequestBody ProductCreateDto dto) {
        productService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}