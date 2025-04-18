package com.reetz.LetsCook.repository;

import com.reetz.LetsCook.entity.FavoritedRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritedRecipeRepository extends JpaRepository<FavoritedRecipe, Long> {
    List<FavoritedRecipe> findByUsuarioId(Long usuarioId);

    Optional<FavoritedRecipe> findByUsuarioIdAndRecipeExternalId(Long usuarioId, Long recipeExternalId);

    void deleteByUsuarioIdAndRecipeExternalId(Long usuarioId, Long recipeExternalId);
}
