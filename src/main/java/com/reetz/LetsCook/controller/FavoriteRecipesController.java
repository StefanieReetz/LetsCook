package com.reetz.LetsCook.controller;

import com.reetz.LetsCook.dto.FavoriteRecipeDTO;
import com.reetz.LetsCook.dto.FavoriteRequestDTO;
import com.reetz.LetsCook.entity.FavoriteRecipe;
import com.reetz.LetsCook.service.FavoriteRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/favorites")
public class FavoriteRecipesController {

    @Autowired
    private FavoriteRecipeService favoriteRecipeService;

    // POST - Favoritar uma receita
    @PostMapping("/{usuarioId}")
    public void favoriteRecipe(
            @PathVariable Long usuarioId,
            @RequestBody Map<String, Long> request) {

        Long recipeId = request.get("recipeId");
        favoriteRecipeService.saveFavorite(usuarioId, recipeId);
    }

    // GET - Listar todas as receitas favoritas de um usuário
    @GetMapping("/{usuarioId}")
    public List<FavoriteRecipeDTO> listFavorites(@PathVariable Long usuarioId) {
        return favoriteRecipeService.listFavorites(usuarioId).stream()
                .map(FavoriteRecipeDTO::new)
                .toList();
    }

    // DELETE - Remover uma receita favorita
    @DeleteMapping("/{usuarioId}/{recipeId}")
    public void unfavoriteRecipe(@PathVariable Long usuarioId, @PathVariable Long recipeId) {
        favoriteRecipeService.removeFavorite(usuarioId, recipeId);
    }
}
