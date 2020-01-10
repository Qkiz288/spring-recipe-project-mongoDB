package com.kkukielka.springrecipeproject.domain;

import lombok.*;

import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
public class Category {

    private String id;
    private String description;
    private Set<Recipe> recipes;

}
