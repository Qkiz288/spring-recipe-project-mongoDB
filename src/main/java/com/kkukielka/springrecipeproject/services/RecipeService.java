package com.kkukielka.springrecipeproject.services;

import com.kkukielka.springrecipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

}
