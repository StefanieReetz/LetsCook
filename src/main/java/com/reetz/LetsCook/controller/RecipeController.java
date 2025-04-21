package com.reetz.LetsCook.controller;

import com.reetz.LetsCook.dto.RecipeResponseDTO;
import com.reetz.LetsCook.entity.Usuario;
import com.reetz.LetsCook.repository.UsuarioRepository;
import com.reetz.LetsCook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario getUsuario(UserDetails userDetails) {
        String username = userDetails.getUsername();
        return usuarioRepository.findByUsername(username);
    }

    @GetMapping("/user")
    public ResponseEntity<List<RecipeResponseDTO>> getRecipesForUser(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = getUsuario(userDetails);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<RecipeResponseDTO> receitas = recipeService.buscarReceitasDoUsuario(usuario);
        return ResponseEntity.ok(receitas);
    }
}



/*
@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public List<RecipeResponseDTO> buscarComMeusIngredientes(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return recipeService.buscarReceitasDoUsuario();
    }


    @GetMapping("/user")
    public ResponseEntity<List<PlaylistDTO>> getAll(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();

        Usuario usuario = usuarioRepository.findByUsername(username);
*/
