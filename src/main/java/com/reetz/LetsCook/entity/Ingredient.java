package com.reetz.LetsCook.entity;

import jakarta.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Ingredient(Long id, String name, Usuario usuario) {
        this.id = id;
        this.name = name;
        this.usuario = usuario;
    }

    public Ingredient(){

    }
}
