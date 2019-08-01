package com.kkukielka.springrecipeproject.controllers;

import com.kkukielka.springrecipeproject.domain.Category;
import com.kkukielka.springrecipeproject.domain.UnitOfMeasure;
import com.kkukielka.springrecipeproject.repositories.CategoryRepository;
import com.kkukielka.springrecipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage() {

        Optional<Category> americanCategory = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> teaSpoonUnitOfMeasure = unitOfMeasureRepository.findByDescription("Teaspoon");

        americanCategory.ifPresent(t -> System.out.println("Category ID: " + t.getId()));
        teaSpoonUnitOfMeasure.ifPresent(t -> System.out.println("UOM ID: " + t.getId()));

        return "index";
    }

}
