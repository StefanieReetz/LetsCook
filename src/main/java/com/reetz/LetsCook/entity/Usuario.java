package com.reetz.LetsCook.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<FavoriteRecipe> favorites;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<FavoriteRecipe> getFavorites() {
        return favorites;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usuario(Long id, String username, String password, List<Ingredient> ingredients, List<FavoriteRecipe> favorites) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.ingredients = ingredients;
        this.favorites = favorites;
    }

    public Usuario() {
    }

    public void setPassword(String encode) {
        this.password = encode;
    }
}
