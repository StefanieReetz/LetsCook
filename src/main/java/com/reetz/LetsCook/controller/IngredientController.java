package com.reetz.LetsCook.controller;

import com.reetz.LetsCook.dto.CreateIngredientDTO;
import com.reetz.LetsCook.dto.IngredientDTO;
import com.reetz.LetsCook.repository.UsuarioRepository;
import com.reetz.LetsCook.service.IngredientService;
import com.reetz.LetsCook.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario getUsuario(UserDetails userDetails) {
        String username = userDetails.getUsername();
        return usuarioRepository.findByUsername(username);
    }


    @GetMapping
    public ResponseEntity<List<IngredientDTO>> getIngredients(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = getUsuario(userDetails);
        return ResponseEntity.ok(ingredientService.getIngredients(usuario));
    }


    @PostMapping
    public ResponseEntity<IngredientDTO> createIngredient(@RequestBody CreateIngredientDTO dto,
                                                @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Usuario usuario = usuarioRepository.findByUsername(username);

        return ResponseEntity.ok(ingredientService.addIngredient(usuario, dto));
    }



    @PutMapping("/{id}")
    public ResponseEntity<IngredientDTO> updateIngredient(@PathVariable Long id,
                                                          @RequestBody CreateIngredientDTO dto,
                                                          @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = getUsuario(userDetails);
        return ResponseEntity.ok(ingredientService.updateIngredient(id, dto, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id,
                                                 @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = getUsuario(userDetails);
        ingredientService.deleteIngredient(id, usuario);
        return ResponseEntity.noContent().build();
    }
}

/*
@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;


    @GetMapping
    public List<IngredientDTO> getIngredients(@AuthenticationPrincipal Usuario usuario) {
        return ingredientService.getIngredients(usuario);
    }

    @PostMapping
    public IngredientDTO addIngredient(@RequestBody CreateIngredientDTO dto,
                                       @AuthenticationPrincipal Usuario usuario) {
        return ingredientService.addIngredient(usuario, dto); // <- ordem corrigida
    }

    @PutMapping("/{id}")
    public IngredientDTO updateIngredient(@PathVariable Long id,
                                          @RequestBody CreateIngredientDTO dto,
                                          @AuthenticationPrincipal Usuario usuario) {
        return ingredientService.updateIngredient(id, dto, usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable Long id,
                                 @AuthenticationPrincipal Usuario usuario) {
        ingredientService.deleteIngredient(id, usuario);
    }
} */
