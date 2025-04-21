package com.reetz.LetsCook.dto;

import java.util.List;

public record RecipeResponseDTO(
        Integer id,
        String title,
        String image,
        List<String> instructions,
        List<String> usedIngredients,
        List<String> missedIngredients
) {}
