package com.reetz.LetsCook.dto;

import java.util.List;
//representar as receitas vindas da API externa, com o que eu quero mostrar
public class RecipeResponseDTO {
    private Integer id; // id da API externa, e tem q ser int
    private String title;
    private String image;
    private Integer usedIngredientCount; //
    private Integer missedIngredientCount; //
    private List<String> usedIngredients;
    private List<String> missedIngredients;

    public Integer getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getImage() {
        return image;
    }
    public Integer getUsedIngredientCount() {
        return usedIngredientCount;
    }
    public Integer getMissedIngredientCount() {
        return missedIngredientCount;
    }
    public List<String> getUsedIngredients() {
        return usedIngredients;
    }
    public List<String> getMissedIngredients() {
        return missedIngredients;
    }
// setter nao e necessario ne?

    public RecipeResponseDTO(Integer id, String title, String image, Integer usedIngredientCount, Integer missedIngredientCount, List<String> usedIngredients, List<String> missedIngredients) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.usedIngredientCount = usedIngredientCount;
        this.missedIngredientCount = missedIngredientCount;
        this.usedIngredients = usedIngredients;
        this.missedIngredients = missedIngredients;
    }

    public RecipeResponseDTO() {
    }

}
