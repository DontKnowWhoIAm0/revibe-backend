package ru.kpfu.itis.revibe.service.impl.user;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.revibe.dto.products.ProductDto;
import ru.kpfu.itis.revibe.entity.user.Favorite;
import ru.kpfu.itis.revibe.entity.products.Product;
import ru.kpfu.itis.revibe.entity.user.User;
import ru.kpfu.itis.revibe.repository.user.FavoriteRepository;
import ru.kpfu.itis.revibe.repository.products.ProductRepository;
import ru.kpfu.itis.revibe.repository.user.UserRepository;
import ru.kpfu.itis.revibe.service.interfaces.user.FavoriteService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository,
                               UserRepository userRepository,
                               ProductRepository productRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void addToFavorites(UUID userId, UUID productId) {
        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        if (favoriteRepository.findByUserAndProduct(user, product).isEmpty()) {
            Favorite f = new Favorite();
            f.setUser(user);
            f.setProduct(product);
            favoriteRepository.save(f);
        }
    }

    @Override
    public void removeFromFavorites(UUID userId, UUID productId) {
        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        favoriteRepository.findByUserAndProduct(user, product)
                .ifPresent(favoriteRepository::delete);
    }

    @Override
    public List<ProductDto> getUserFavorites(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return favoriteRepository.findByUser(user).stream().map(f -> {
            Product p = f.getProduct();
            ProductDto dto = new ProductDto();
            dto.setArticle(p.getArticle());
            dto.setName(p.getName());
            dto.setPrice(p.getPrice());
            dto.setImageUrl(p.getImageUrl());
            dto.setBrand(p.getBrand());
            dto.setColor(p.getColor());
            dto.setSize(p.getSize());
            dto.setGender(p.getGender());
            return dto;
        }).collect(Collectors.toList());
    }
}