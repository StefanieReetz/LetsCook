package com.reetz.LetsCook.dto;

import com.reetz.LetsCook.entity.FavoriteRecipe;

public record FavoriteRecipeDTO(Long id, Long recipeId, String title, String image) {
    public FavoriteRecipeDTO(FavoriteRecipe recipe) {
        this(recipe.getId(), recipe.getRecipeExternalId(), recipe.getTitle(), recipe.getImage());
    }
}
