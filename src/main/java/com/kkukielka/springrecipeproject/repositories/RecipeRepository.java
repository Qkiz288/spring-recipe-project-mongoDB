package com.kkukielka.springrecipeproject.repositories;

import com.kkukielka.springrecipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
