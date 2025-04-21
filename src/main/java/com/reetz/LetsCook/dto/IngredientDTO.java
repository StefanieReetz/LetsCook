package com.reetz.LetsCook.dto;


import com.reetz.LetsCook.entity.Ingredient;


public record IngredientDTO(
        Long id,
        String name,
        Integer quantity
) {}
/*
public class IngredientDTO {
    private Long id;
    private String name;
    private Integer quantity;

    public IngredientDTO() {
        // construtor vazio (usado pelo Jackson ou frameworks)
    }

    public IngredientDTO(Long id, String name, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }



    public IngredientDTO(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.quantity = ingredient.getQuantity();
    }

    public IngredientDTO(Long id, String name) {

    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}*/
/*

public class IngredientDTO {
    private Long id;
    private String name;

    public IngredientDTO() {}

    public IngredientDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
*/
