package com.reetz.LetsCook.repository;

import com.reetz.LetsCook.entity.Ingredient;
import com.reetz.LetsCook.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByUsuarioId(Long usuarioId);
    Optional<Ingredient> findByIdAndUsuario(Long id, Usuario usuario);

    Optional<Ingredient> findByUsuario(Usuario usuario);
}
