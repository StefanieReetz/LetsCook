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
    private List<FavoritedRecipe> favorites;

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

    public List<FavoritedRecipe> getFavorites() {
        return favorites;
    }



    public Usuario(Long id, String username, String password, List<Ingredient> ingredients, List<FavoritedRecipe> favorites) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.ingredients = ingredients;
        this.favorites = favorites;
    }

    public Usuario() {
    }

}
