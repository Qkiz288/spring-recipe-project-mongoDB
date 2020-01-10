package com.kkukielka.springrecipeproject.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {

    private Long id;
    private Recipe recipe;
    private String recipeNotes;
}
