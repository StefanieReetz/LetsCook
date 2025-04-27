package com.reetz.LetsCook.controller;

import com.reetz.LetsCook.dto.FavoriteRecipeDTO;
import com.reetz.LetsCook.entity.Usuario;
import com.reetz.LetsCook.repository.UsuarioRepository;
import com.reetz.LetsCook.service.FavoriteRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/favorites")
public class FavoriteRecipesController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FavoriteRecipeService favoriteRecipeService;

    // POST - Favoritar uma receita
    @PostMapping
    public void favoriteRecipe(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestBody Map<String, Long> request) {
        Long recipeId = request.get("recipeId");
        favoriteRecipeService.favoriteRecipe(userDetails, recipeId);
    }

    // GET - Listar todas as receitas favoritas de um usuário
    @GetMapping
    public List<FavoriteRecipeDTO> listFavorites(@AuthenticationPrincipal UserDetails userDetails) {
        return favoriteRecipeService.listFavorites(userDetails);
    }

    // DELETE - Remover uma receita favorita
    @DeleteMapping("/{recipeId}")
    public void unfavoriteRecipe(@PathVariable Long recipeId,
                                 @AuthenticationPrincipal Usuario usuario) {
        if (usuario == null) {
            // Recupera o username do token de autenticação
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            usuario = usuarioRepository.findByUsername(username);

            if (usuario == null) {
                throw new RuntimeException("Usuário não encontrado com o nome: " + username);
            }
        }
        favoriteRecipeService.removeFavorite(usuario, recipeId);
    }
}
