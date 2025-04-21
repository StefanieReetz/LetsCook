package com.reetz.LetsCook.service;

import com.reetz.LetsCook.dto.RecipeResponseDTO;
import com.reetz.LetsCook.entity.Ingredient;
import com.reetz.LetsCook.entity.Usuario;
import com.reetz.LetsCook.external.SpoonacularRecipe;
import com.reetz.LetsCook.external.SpoonacularService;
import com.reetz.LetsCook.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private SpoonacularService spoonacularService;

    public List<RecipeResponseDTO> buscarReceitasDoUsuario(Usuario usuario) {
        List<Ingredient> meusIngredientes = ingredientRepository.findByUsuarioId(usuario.getId());
        String ingredientes = meusIngredientes.stream()
                .map(Ingredient::getName)
                .collect(Collectors.joining(","));

        List<SpoonacularRecipe> receitas = spoonacularService.buscarReceitas(ingredientes, 5);

        return receitas.stream().map(receita -> {
            List<String> instrucoes = spoonacularService.buscarInstrucoes(receita.getId());

            List<String> usados = receita.getUsedIngredients().stream()
                    .map(SpoonacularRecipe.IngredientInfo::getName)
                    .toList();

            List<String> faltando = receita.getMissedIngredients().stream()
                    .map(SpoonacularRecipe.IngredientInfo::getName)
                    .toList();

            return new RecipeResponseDTO(
                    receita.getId(),
                    receita.getTitle(),
                    receita.getImage(),
                    instrucoes,
                    usados,
                    faltando
            );
        }).toList();
    }
}
