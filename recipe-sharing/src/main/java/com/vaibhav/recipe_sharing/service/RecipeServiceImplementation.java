package com.vaibhav.recipe_sharing.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaibhav.recipe_sharing.model.Recipe;
import com.vaibhav.recipe_sharing.model.User;
import com.vaibhav.recipe_sharing.repository.RecipeRepository;

@Service
public class RecipeServiceImplementation implements RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public Recipe createRecipe(Recipe recipe, User user) {
		// TODO Auto-generated method stub
		Recipe createdRecipe=new Recipe();
		createdRecipe.setTitle(recipe.getTitle());
		createdRecipe.setImage(recipe.getImage());
		createdRecipe.setDescription(recipe.getDescription());
		createdRecipe.setUser(user);
		createdRecipe.setCreatedAT(LocalDateTime.now());
		
		return recipeRepository.save(createdRecipe);
	}

	@Override
	public Recipe findRecipeById(Long id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Recipe> opt=recipeRepository.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("Recipe not found with id"+id);
	}

	@Override
	public void deleteRecipe(Long id) throws Exception {
		// TODO Auto-generated method stub
		findRecipeById(id);
		recipeRepository.deleteById(id);
		
	}

	@Override
	public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
		// TODO Auto-generated method stub
		Recipe oldRecipe=findRecipeById(id);
		
		if(recipe.getTitle()!=null) {
			oldRecipe.setTitle(recipe.getTitle());
		}
		if(recipe.getImage()!=null) {
			oldRecipe.setImage(null);
		}
		if(recipe.getDescription()!=null) {
			oldRecipe.setDescription(null);
		}
		return recipeRepository.save(oldRecipe);
	}

	@Override
	public List<Recipe> findAllRecipe() {
		// TODO Auto-generated method stub
		return recipeRepository.findAll();
	}

	@Override
	public Recipe likeRecipe(Long recipeId, User user) throws Exception {
		// TODO Auto-generated method stub
		Recipe recipe=findRecipeById(recipeId);
		
		if(recipe.getLikes().contains(user.getId())) {
			recipe.getLikes().remove(user.getId());
		}else {
			recipe.getLikes().add(user.getId());
		}
		return recipeRepository.save(recipe);
	}

	

}
