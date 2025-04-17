package com.reetz.LetsCook.controller;

import com.reetz.LetsCook.entity.FavoritedRecipe;
import com.reetz.LetsCook.service.FavoritedRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoritedRecipeController {

    @Autowired
    private FavoritedRecipeService favoritedRecipeService;

    // POST - Favoritar uma receita
    @PostMapping("/{usuarioId}")
    public void favoriteRecipe(
            @PathVariable Long usuarioId,
            @RequestParam Long recipeId,
            @RequestParam String title,
            @RequestParam String image) {
        favoritedRecipeService.saveFavorite(usuarioId, recipeId, title, image);
    }

    // GET - Listar todas as receitas favoritas de um usuário
    @GetMapping("/{usuarioId}")
    public List<FavoritedRecipe> getFavorites(@PathVariable Long usuarioId) {
        return favoritedRecipeService.findFavoritesByUser(usuarioId);
    }

    // DELETE - Remover uma receita favorita
    @DeleteMapping("/{usuarioId}/{recipeId}")
    public void unfavoriteRecipe(@PathVariable Long usuarioId, @PathVariable Long recipeId) {
        favoritedRecipeService.removeFavorite(usuarioId, recipeId);
    }
}
