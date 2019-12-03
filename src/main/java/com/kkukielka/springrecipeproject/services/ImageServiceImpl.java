package com.kkukielka.springrecipeproject.services;

import com.kkukielka.springrecipeproject.domain.Recipe;
import com.kkukielka.springrecipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {

        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {
        log.debug("Received a file");

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException(String.format("Recipe with ID = %s not found", recipeId));
        }

        Recipe recipe = recipeOptional.get();

        try {
            Byte[] byteFile = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()) {
                byteFile[i++] = b;
            }

            recipe.setImage(byteFile);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("Error occured");
            e.printStackTrace();
        }



    }
}
