package com.reetz.LetsCook.service;

import com.reetz.LetsCook.entity.FavoritedRecipe;
import com.reetz.LetsCook.entity.Usuario;
import com.reetz.LetsCook.repository.FavoritedRecipeRepository;
import com.reetz.LetsCook.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritedRecipeService {

    @Autowired
    private FavoritedRecipeRepository favoritedRecipeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public FavoritedRecipeService(FavoritedRecipeRepository favoritedRecipeRepository, UsuarioRepository usuarioRepository) {
        this.favoritedRecipeRepository = favoritedRecipeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public FavoritedRecipe saveFavorite(Long usuarioId, Long recipeExternalId, String title, String image) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Check if already favorited
        Optional<FavoritedRecipe> existing = favoritedRecipeRepository.findByUsuarioIdAndRecipeExternalId(usuarioId, recipeExternalId);
        if (existing.isPresent()) {
            return existing.get();
        }

        FavoritedRecipe recipe = new FavoritedRecipe(null, recipeExternalId, title, image, usuario);
        return favoritedRecipeRepository.save(recipe);
    }

    public List<FavoritedRecipe> listFavorites(Long usuarioId) {
        return favoritedRecipeRepository.findByUsuarioId(usuarioId);
    }

    public void removeFavorite(Long usuarioId, Long recipeExternalId) {
        favoritedRecipeRepository.deleteByUsuarioIdAndRecipeExternalId(usuarioId, recipeExternalId);
    }

    public boolean isFavorited(Long usuarioId, Long recipeExternalId) {
        return favoritedRecipeRepository.findByUsuarioIdAndRecipeExternalId(usuarioId, recipeExternalId).isPresent();
    }

    public List<FavoritedRecipe> findFavoritesByUser(Long usuarioId) {
        return favoritedRecipeRepository.findByUsuarioId(usuarioId);
    }
}
