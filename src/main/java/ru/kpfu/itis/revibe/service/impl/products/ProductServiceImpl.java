package ru.kpfu.itis.revibe.service.impl.products;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.revibe.dto.products.BranchDto;
import ru.kpfu.itis.revibe.dto.products.ProductCreateDto;
import ru.kpfu.itis.revibe.dto.products.ProductDetailDto;
import ru.kpfu.itis.revibe.entity.products.Product;
import ru.kpfu.itis.revibe.repository.products.ProductRepository;
import ru.kpfu.itis.revibe.service.interfaces.products.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDetailDto getProduct(String article) {
        Product p = productRepository.findById(article).orElseThrow();
        ProductDetailDto dto = new ProductDetailDto();
        dto.setArticle(p.getArticle());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setSold(p.isSold());
        dto.setImageUrl(p.getImageUrl());
        return dto;
    }

    @Override
    @Transactional
    public List<ProductDetailDto> getAllProducts() {
        return productRepository.findAll().stream().map(p -> {
            ProductDetailDto dto = new ProductDetailDto();
            dto.setArticle(p.getArticle());
            dto.setName(p.getName());
            dto.setDescription(p.getDescription());
            dto.setPrice(p.getPrice());
            dto.setCategory(p.getCategory());
            dto.setGender(p.getGender());
            dto.setColor(p.getColor());
            dto.setBrand(p.getBrand());
            dto.setSize(p.getSize());
            dto.setCondition(p.getCondition());
            dto.setSold(p.isSold());
            dto.setImageUrl(p.getImageUrl());

            if (p.getBranch() != null) {
                BranchDto branchDto = new BranchDto();
                branchDto.setId(p.getBranch().getId());
                branchDto.setName(p.getBranch().getName());
                branchDto.setCity(p.getBranch().getCity());
                branchDto.setAddress(p.getBranch().getAddress());
                dto.setBranch(branchDto);
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public Product addProduct(ProductCreateDto dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setDescription(dto.getDescription());
        p.setCategory(dto.getCategory());
        p.setSize(dto.getSize());
        p.setCondition(dto.getCondition());
        p.setImageUrl(dto.getImageUrl());
        return productRepository.save(p);
    }

    @Override
    public Product updateProduct(String article, ProductCreateDto dto) {
        Product p = productRepository.findById(article).orElseThrow();
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        p.setDescription(dto.getDescription());
        return productRepository.save(p);
    }

    @Override
    public void deleteProduct(String article) {
        productRepository.deleteById(article);
    }
}