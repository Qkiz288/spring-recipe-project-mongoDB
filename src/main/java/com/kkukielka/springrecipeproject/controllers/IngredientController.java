package com.kkukielka.springrecipeproject.controllers;

import com.kkukielka.springrecipeproject.commands.IngredientCommand;
import com.kkukielka.springrecipeproject.commands.RecipeCommand;
import com.kkukielka.springrecipeproject.commands.UnitOfMeasureCommand;
import com.kkukielka.springrecipeproject.services.IngredientService;
import com.kkukielka.springrecipeproject.services.RecipeService;
import com.kkukielka.springrecipeproject.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;
    private UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService,
                                IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
        log.debug(String.format("Get ingredients for recipe %s", recipeId));

        // use command object to avoid lazy load errors in Thymeleaf
        model.addAttribute("recipe", recipeService.findCommandById(recipeId).block());

        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        log.debug(String.format("Get ingredient id = %s detail for recipeId = %s", ingredientId, recipeId));

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndId(recipeId, ingredientId).block());

        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {

        log.debug("I'm in newIngredient");

        Mono<RecipeCommand> recipeCommand = recipeService.findCommandById(recipeId);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);
        model.addAttribute("ingredient", ingredientCommand);

        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms().collectList().block());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndId(recipeId, id).block());

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms().collectList().block());

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand).block();

        String savedRecipeId = savedCommand.getRecipeId();
        String savedIngredientId = savedCommand.getId();

        log.debug(String.format("Saving ingredient for Recipe Id = %s", savedRecipeId));
        log.debug(String.format("Saving Ingredient Id = %s", savedIngredientId));

        return "redirect:/recipe/" + savedRecipeId + "/ingredient/" + savedIngredientId + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId) {
        log.debug(String.format("Removing ingredient with id = %s for recipe id = %s", ingredientId, recipeId));
        ingredientService.deleteById(recipeId, ingredientId).block();

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

}
