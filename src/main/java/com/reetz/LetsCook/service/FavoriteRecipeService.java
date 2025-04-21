package com.reetz.LetsCook.service;

import com.reetz.LetsCook.entity.FavoriteRecipe;
import com.reetz.LetsCook.entity.Usuario;
import com.reetz.LetsCook.external.SpoonacularRecipeDetails;
import com.reetz.LetsCook.external.SpoonacularService;
import com.reetz.LetsCook.repository.FavoriteRecipeRepository;
import com.reetz.LetsCook.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteRecipeService {

    @Autowired
    private SpoonacularService spoonacularService;

    @Autowired
    private FavoriteRecipeRepository favoriteRecipeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public FavoriteRecipeService(FavoriteRecipeRepository favoriteRecipeRepository,
                                 UsuarioRepository usuarioRepository) {
        this.favoriteRecipeRepository = favoriteRecipeRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public FavoriteRecipe saveFavorite(Long usuarioId, Long recipeExternalId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Já está favoritada?
        Optional<FavoriteRecipe> existing = favoriteRecipeRepository
                .findByUsuarioIdAndRecipeExternalId(usuarioId, recipeExternalId);
        if (existing.isPresent()) {
            return existing.get();
        }

        // 🧠 Buscar da Spoonacular API
        SpoonacularRecipeDetails detalhes = spoonacularService.buscarDetalhes(recipeExternalId);

        if (detalhes == null) {
            throw new RuntimeException("Erro ao buscar detalhes da receita");
        }

        FavoriteRecipe recipe = new FavoriteRecipe(
                null,
                recipeExternalId,
                detalhes.title(),
                detalhes.image(),
                usuario
        );

        return favoriteRecipeRepository.save(recipe);
    }


//    public FavoriteRecipe saveFavorite(Long usuarioId,
//                                       Long recipeExternalId) {
//        Usuario usuario = usuarioRepository.findById(usuarioId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//        // Check if already favorited
//        Optional<FavoriteRecipe> existing = favoriteRecipeRepository.findByUsuarioIdAndRecipeExternalId(usuarioId, recipeExternalId);
//        if (existing.isPresent()) {
//            return existing.get();
//        }
//
//        FavoriteRecipe recipe = new FavoriteRecipe(null, recipeExternalId, "", "", usuario);
//        return favoriteRecipeRepository.save(recipe);
//    }

    public List<FavoriteRecipe> listFavorites(Long usuarioId) {
        return favoriteRecipeRepository.findByUsuarioId(usuarioId);
    }
    /*
    public List<FavoriteRecipeDTO> listFavorites(Long usuarioId) {
    return favoriteRecipeRepository.findByUsuarioId(usuarioId).stream()
            .map(FavoriteRecipeDTO::new)
            .toList();
}
     */

    public void removeFavorite(Long usuarioId, Long recipeExternalId) {
        favoriteRecipeRepository.deleteByUsuarioIdAndRecipeExternalId(usuarioId, recipeExternalId);
    }

    public boolean isFavorited(Long usuarioId, Long recipeExternalId) {
        return favoriteRecipeRepository.findByUsuarioIdAndRecipeExternalId(usuarioId, recipeExternalId).isPresent();
    }

    public List<FavoriteRecipe> findFavoritesByUser(Long usuarioId) {
        return favoriteRecipeRepository.findByUsuarioId(usuarioId);
    }
}
