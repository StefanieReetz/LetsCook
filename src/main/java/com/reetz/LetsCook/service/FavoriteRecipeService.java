package com.reetz.LetsCook.service;

import com.reetz.LetsCook.dto.FavoriteRecipeDTO;
import com.reetz.LetsCook.entity.FavoriteRecipe;
import com.reetz.LetsCook.entity.Usuario;
import com.reetz.LetsCook.external.SpoonacularRecipeDetails;
import com.reetz.LetsCook.external.SpoonacularService;
import com.reetz.LetsCook.repository.FavoriteRecipeRepository;
import com.reetz.LetsCook.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FavoriteRecipeService {

    @Autowired
    private SpoonacularService spoonacularService;

    @Autowired
    private FavoriteRecipeRepository favoriteRecipeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public void favoriteRecipe(UserDetails userDetails, Long recipeExternalId) {
        // Recupera o usuário a partir dos detalhes de autenticação
        Usuario usuario = getUsuario(userDetails);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        // Já está favoritada?
        Optional<FavoriteRecipe> existing = favoriteRecipeRepository
                .findByUsuarioIdAndRecipeExternalId(usuario.getId(), recipeExternalId);
        if (existing.isPresent()) {
            return; // Se já foi favoritada, não faz nada
        }

        // Buscar da Spoonacular API
        SpoonacularRecipeDetails detalhes = spoonacularService.buscarDetalhes(recipeExternalId);

        if (detalhes == null) {
            throw new RuntimeException("Erro ao buscar detalhes da receita");
        }

        // Cria e salva a receita favorita
        FavoriteRecipe recipe = new FavoriteRecipe(
                null,
                recipeExternalId,
                detalhes.title(),
                detalhes.image(),
                usuario
        );
        favoriteRecipeRepository.save(recipe);
    }


    public List<FavoriteRecipeDTO> listFavorites(UserDetails userDetails) {
        // Recupera o usuário a partir dos detalhes de autenticação
        Usuario usuario = getUsuario(userDetails);
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        // Recupera todas as receitas favoritas do usuário
        return favoriteRecipeRepository.findByUsuarioId(usuario.getId()).stream()
                .map(FavoriteRecipeDTO::new)
                .toList();
    }

    @Transactional
    public void removeFavorite(Usuario usuario, Long recipeExternalId) {
        favoriteRecipeRepository.deleteByUsuarioIdAndRecipeExternalId(usuario.getId(), recipeExternalId);
    }

    // Método privado para recuperar o usuário a partir do UserDetails
    private Usuario getUsuario(UserDetails userDetails) {
        String username = userDetails.getUsername();
        return usuarioRepository.findByUsername(username);
    }
}
    /* um jeito diferente de fazer, talvez ate melhor para o listar favorites
    public List<FavoriteRecipeDTO> listFavorites(Long usuarioId) {
    return favoriteRecipeRepository.findByUsuarioId(usuarioId).stream()
            .map(FavoriteRecipeDTO::new)
            .toList();
}
     */