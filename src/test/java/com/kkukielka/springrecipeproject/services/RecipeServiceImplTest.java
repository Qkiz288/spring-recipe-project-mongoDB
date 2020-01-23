package com.kkukielka.springrecipeproject.services;

import com.kkukielka.springrecipeproject.converters.RecipeCommandToRecipe;
import com.kkukielka.springrecipeproject.converters.RecipeToRecipeCommand;
import com.kkukielka.springrecipeproject.domain.Recipe;
import com.kkukielka.springrecipeproject.repositories.reactive.RecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    private RecipeService recipeService;

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeReactiveRepository,
                recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        Recipe recipeReturned = recipeService.findById("1").block();

        assertNotNull(recipeReturned);
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, never()).findAll();


    }

    @Test
    public void getRecipes() {

        Recipe recipe = new Recipe();
        Set<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeReactiveRepository.findAll()).thenReturn(Flux.just(recipe));

        List<Recipe> recipes = recipeService.getRecipes().collectList().block();

        assertEquals(1L, recipes.size());
        verify(recipeReactiveRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        //given
        String idToDelete = "2";

        when(recipeReactiveRepository.deleteById(anyString())).thenReturn(Mono.empty());

        //when
        recipeService.deleteById(idToDelete);

        //then
        verify(recipeReactiveRepository, times(1)).deleteById(anyString());
    }


}