package ru.kpfu.itis.revibe.mapper;

import org.springframework.stereotype.Component;
import ru.kpfu.itis.revibe.dto.products.ProductCreateDto;
import ru.kpfu.itis.revibe.dto.products.ProductDto;
import ru.kpfu.itis.revibe.dto.products.ProductDetailDto;
import ru.kpfu.itis.revibe.dto.products.BranchDto;
import ru.kpfu.itis.revibe.entity.products.Branch;
import ru.kpfu.itis.revibe.entity.products.Product;

@Component
public class ProductMapper {

    public Product toEntity(ProductCreateDto dto, Branch branch) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setGender(dto.getGender());
        product.setColor(dto.getColor());
        product.setBrand(dto.getBrand());
        product.setSize(dto.getSize());
        product.setCondition(dto.getCondition());
        product.setImageUrl(dto.getImageUrl());
        product.setBranch(branch);
        product.setSold(false);
        return product;
    }

    public ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setArticle(product.getArticle());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setImageUrl(product.getImageUrl());
        dto.setGender(product.getGender());
        dto.setColor(product.getColor());
        dto.setBrand(product.getBrand());
        dto.setSize(product.getSize());
        return dto;
    }

    public ProductDetailDto toDetailDto(Product product) {
        ProductDetailDto dto = new ProductDetailDto();
        dto.setArticle(product.getArticle());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        dto.setGender(product.getGender());
        dto.setColor(product.getColor());
        dto.setBrand(product.getBrand());
        dto.setSize(product.getSize());
        dto.setCondition(product.getCondition());
        dto.setSold(product.isSold());
        dto.setImageUrl(product.getImageUrl());
        if (product.getBranch() != null) {
            BranchDto branchDto = new BranchDto();
            branchDto.setId(product.getBranch().getId());
            branchDto.setName(product.getBranch().getName());
            branchDto.setCity(product.getBranch().getCity());
            branchDto.setAddress(product.getBranch().getAddress());
            dto.setBranch(branchDto);
        }
        return dto;
    }
}