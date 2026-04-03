package ru.kpfu.itis.revibe.controller.client;

import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.revibe.dto.products.ProductDto;
import ru.kpfu.itis.revibe.service.interfaces.user.FavoriteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {

    private final FavoriteService favoriteService;
    public FavoritesController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public void addFavorite(@RequestParam UUID userId, @RequestParam UUID productId) {
        favoriteService.addToFavorites(userId, productId);
    }

    @DeleteMapping("/{productId}")
    public void removeFavorite(@RequestParam UUID userId, @PathVariable UUID productId) {
        favoriteService.removeFromFavorites(userId, productId);
    }

    @GetMapping
    public List<ProductDto> getFavorites(@RequestParam UUID userId) {
        return favoriteService.getUserFavorites(userId);
    }
}