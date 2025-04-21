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

    private Integer quantity;

    public Ingredient(Long id, String name, Usuario usuario) {
        this.id = id;
        this.name = name;
        this.usuario = usuario;
    }

    public Ingredient(){

    }

    public Ingredient(String name, Integer quantity, Usuario usuario) {
        this.name = name;
        this.usuario = usuario;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
