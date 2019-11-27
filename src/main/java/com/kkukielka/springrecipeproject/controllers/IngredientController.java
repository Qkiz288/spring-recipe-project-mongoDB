package com.kkukielka.springrecipeproject.controllers;

import com.kkukielka.springrecipeproject.services.IngredientService;
import com.kkukielka.springrecipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug(String.format("Get ingredients for recipe %s", recipeId));

        // use command object to avoid lazy load errors in Thymeleaf
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        log.debug(String.format("Get ingredient id = %s detail for recipeId = %s", ingredientId, recipeId));

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        return "recipe/ingredient/show";
    }

}
