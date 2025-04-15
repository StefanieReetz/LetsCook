package com.reetz.LetsCook.entity;

import jakarta.persistence.*;

@Entity
public class FavoritedRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long recipeExternalId; // ID do Spoonacular
    private String title;
    private String image;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public FavoritedRecipe(Long id, Long recipeIdExterno, String title, String image, Usuario usuario) {
        this.id = id;
        this.recipeExternalId = recipeIdExterno;
        this.title = title;
        this.image = image;
        this.usuario = usuario;
    }
    public FavoritedRecipe(){}
}
