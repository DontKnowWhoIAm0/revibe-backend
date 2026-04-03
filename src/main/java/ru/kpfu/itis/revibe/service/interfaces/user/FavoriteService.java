package ru.kpfu.itis.revibe.service.interfaces.user;

import ru.kpfu.itis.revibe.dto.products.ProductDto;
import java.util.List;
import java.util.UUID;

public interface FavoriteService {
    void addToFavorites(UUID userId, UUID productId);
    void removeFromFavorites(UUID userId, UUID productId);
    List<ProductDto> getUserFavorites(UUID userId);
}