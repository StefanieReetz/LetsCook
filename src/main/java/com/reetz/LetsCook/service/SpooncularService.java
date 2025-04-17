package com.reetz.LetsCook.service;

import com.reetz.LetsCook.dto.RecipeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class SpooncularService {

    @Value("${spoonacular.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;


    public List<RecipeResponseDTO> buscarReceitas(String ingredientes, int quantidade) {
        String url = "https://api.spoonacular.com/recipes/findByIngredients" +
                "?ingredients=" + ingredientes +
                "&number=" + quantidade +
                "&ranking=1" +
                "&ignorePantry=true" +
                "&apiKey=" + apiKey;

        ResponseEntity<RecipeResponseDTO[]> response = restTemplate.getForEntity(url, RecipeResponseDTO[].class);
        return Arrays.asList(response.getBody());
    }
}
