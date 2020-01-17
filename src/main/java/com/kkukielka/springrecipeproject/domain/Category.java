package com.kkukielka.springrecipeproject.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Document
public class Category {

    @Id
    private String id;
    private String description;
    private Set<Recipe> recipes;

}
