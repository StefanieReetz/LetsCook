package com.reetz.LetsCook.service;

import com.reetz.LetsCook.dto.CreateIngredientDTO;
import com.reetz.LetsCook.dto.IngredientDTO;
import com.reetz.LetsCook.entity.Ingredient;
import com.reetz.LetsCook.entity.Usuario;
import com.reetz.LetsCook.repository.IngredientRepository;
import com.reetz.LetsCook.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

/*
    public IngredientDTO addIngredient(CreateIngredientDTO dto, Usuario usuario) {

        // Pega o login (username) do usuário autenticado via token
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Ingredient ingredient = new Ingredient(dto.name(), dto.quantity(), usuario);
        ingredientRepository.save(ingredient);
        return new IngredientDTO(ingredient.getId(), ingredient.getName());
    }
*/

    public IngredientDTO addIngredient(Usuario usuario, CreateIngredientDTO dto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(dto.name());
        ingredient.setQuantity(dto.quantity());
        ingredient.setUsuario(usuario);

        Ingredient saved = ingredientRepository.save(ingredient);
        return toDTO(saved);
    }



    public IngredientDTO updateIngredient(Long id, CreateIngredientDTO dto, Usuario usuario) {
        Ingredient ingredient = ingredientRepository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado"));

        ingredient.setName(dto.name());
        ingredient.setQuantity(dto.quantity());

        Ingredient updated = ingredientRepository.save(ingredient);
        return toDTO(updated);
    }

    public void deleteIngredient(Long id, Usuario usuario) {
        Ingredient ingredient = ingredientRepository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado"));

        ingredientRepository.delete(ingredient);
    }

    public List<IngredientDTO> getIngredients(Usuario usuario) {
        return ingredientRepository.findByUsuario(usuario).stream()
                .map(this::toDTO)
                .toList();
    }

    private IngredientDTO toDTO(Ingredient ingredient) {
        return new IngredientDTO(ingredient.getId(), ingredient.getName(), ingredient.getQuantity());
    }
}
/*
    // atualizar
    public IngredientDTO updateIngredient(Long id, CreateIngredientDTO dto, Usuario usuario) {
        // Pega o username (login) do usuário autenticado via token
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Busca o ingrediente do usuário específico
        Ingredient ingredient = ingredientRepository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado"));

        // Atualiza os dados do ingrediente
        ingredient.setName(dto.name());
        ingredient.setQuantity(dto.quantity());

        // Salva e retorna o ingrediente atualizado
        Ingredient updated = ingredientRepository.save(ingredient);
        return new IngredientDTO(updated); // Retorna o DTO com as informações do ingrediente atualizado
 }

    // public void deleteIngredient(Long id, Usuario usuario) {
    public void deleteIngredient(Long id) {
        // Pega o username (login) do usuário autenticado via token
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Busca o usuário no banco pelo username (login)
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Busca o ingrediente do usuário específico
        Ingredient ingredient = ingredientRepository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado"));

        // Exclui o ingrediente
        ingredientRepository.delete(ingredient);
    }
        // Pega o username (login) do usuário autenticado via token
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Busca o usuário no banco pelo username (login)
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Busca o ingrediente do usuário específico
        Ingredient ingredient = ingredientRepository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new RuntimeException("Ingrediente não encontrado"));

        // Exclui o ingrediente
        ingredientRepository.delete(ingredient);

    /*    Ingredient ingredient = ingredientRepository.findByIdAndUsuario(id, usuario)
                .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado"));

        ingredientRepository.delete(ingredient); */

