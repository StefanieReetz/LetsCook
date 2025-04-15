package com.reetz.LetsCook.repository;

import com.reetz.LetsCook.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByUsuarioId(Long usuarioId);
}
