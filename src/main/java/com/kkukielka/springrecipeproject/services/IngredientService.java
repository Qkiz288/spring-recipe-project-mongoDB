package com.kkukielka.springrecipeproject.services;

import com.kkukielka.springrecipeproject.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndId(String recipeId, String id);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void deleteById(String recipeId, String id);
}
