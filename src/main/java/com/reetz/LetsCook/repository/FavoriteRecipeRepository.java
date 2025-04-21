package com.reetz.LetsCook.repository;

import com.reetz.LetsCook.entity.FavoriteRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe, Long> {
    List<FavoriteRecipe> findByUsuarioId(Long usuarioId);

    Optional<FavoriteRecipe> findByUsuarioIdAndRecipeExternalId(Long usuarioId, Long recipeExternalId);

    void deleteByUsuarioIdAndRecipeExternalId(Long usuarioId, Long recipeExternalId);
}
