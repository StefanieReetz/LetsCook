package com.reetz.LetsCook.external;

import com.reetz.LetsCook.dto.RecipeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpoonacularService {

    @Value("${spoonacular.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;


    public List<SpoonacularRecipe> buscarReceitas(String ingredientes, int quantidade) {
        try {
            String url = "https://api.spoonacular.com/recipes/findByIngredients" +
                    "?ingredients=" + ingredientes +
                    "&number=" + quantidade +
                    "&ranking=1" +
                    "&ignorePantry=true" +
                    "&apiKey=" + apiKey;

            ResponseEntity<SpoonacularRecipe[]> response = restTemplate.getForEntity(url, SpoonacularRecipe[].class);
            return Arrays.asList(response.getBody());
        } catch (Exception e) {
            return List.of(); // Retorna lista vazia se der erro
        }
    }

    public List<String> buscarInstrucoes(Integer recipeId) {
        try {
            String url = "https://api.spoonacular.com/recipes/" + recipeId + "/analyzedInstructions?apiKey=" + apiKey;
            ResponseEntity<InstructionBlock[]> response = restTemplate.getForEntity(url, InstructionBlock[].class);

            if (response.getBody() != null && response.getBody().length > 0) {
                return response.getBody()[0].getSteps().stream()
                        .map(Step::getStep)
                        .collect(Collectors.toList());
            }

            return List.of();
        } catch (Exception e) {
            return List.of();
        }
    }

    // Classes auxiliares para representar as instruções
    public static class InstructionBlock {
        private List<Step> steps;

        public List<Step> getSteps() {
            return steps;
        }

        public void setSteps(List<Step> steps) {
            this.steps = steps;
        }
    }

    public static class Step {
        private String step;

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }
    }
}

