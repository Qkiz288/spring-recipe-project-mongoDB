package com.kkukielka.springrecipeproject.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() {
        Long expectedId = 4L;

        category.setId(expectedId);

        assertEquals("Category id is different than expected",
                expectedId, category.getId());
    }

    @Test
    public void getDescription() {
        String expectedDescription = "test desc";

        category.setDescription(expectedDescription);

        assertEquals("Category description is different than expected",
                expectedDescription, category.getDescription());
    }

    @Test
    public void getRecipes() {
        Recipe[] recipes = { new Recipe(), new Recipe(), new Recipe() };
        Set<Recipe> expectedRecipes = new HashSet<>(Arrays.asList(recipes));

        category.setRecipes(expectedRecipes);

        assertEquals("Category recipes are different than expected",
                expectedRecipes, category.getRecipes());
    }
}