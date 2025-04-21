package com.reetz.LetsCook.entity;

import jakarta.persistence.*;

@Entity
public class FavoriteRecipe {
    // id do favoritado, nao sei se isso vai ser util
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long recipeExternalId; // ID do Spoonacular
    private String title;
    private String image;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public FavoriteRecipe(Long id, Long recipeIdExterno, String title, String image, Usuario usuario) {
        this.id = id;
        this.recipeExternalId = recipeIdExterno;
        this.title = title;
        this.image = image;
        this.usuario = usuario;
    }
    public FavoriteRecipe(){}

    public Long getId() {
        return id;
    }

    public Long getRecipeExternalId() {
        return recipeExternalId;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
