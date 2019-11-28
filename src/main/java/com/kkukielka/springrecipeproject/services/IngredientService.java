package com.kkukielka.springrecipeproject.services;

import com.kkukielka.springrecipeproject.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndId(Long recipeId, Long id);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
}
