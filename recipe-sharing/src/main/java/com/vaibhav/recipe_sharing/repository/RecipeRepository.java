package com.vaibhav.recipe_sharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.recipe_sharing.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
