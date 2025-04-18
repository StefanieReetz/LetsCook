package com.reetz.LetsCook.dto;


public record IngredientDTO(Long id, String name) {
}
//
//public class IngredientDTO {
//    private Long id;
//    private String name;
//
//    public IngredientDTO() {}
//
//    public IngredientDTO(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}