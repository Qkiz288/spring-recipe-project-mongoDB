package com.kkukielka.springrecipeproject.services;

import com.kkukielka.springrecipeproject.domain.Recipe;
import com.kkukielka.springrecipeproject.repositories.reactive.RecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {

    @Mock
    private RecipeReactiveRepository recipeReactiveRepository;

    private ImageService imageService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        imageService = new ImageServiceImpl(recipeReactiveRepository);
    }

    @Test
    public void saveImageFile() throws IOException {
        // given
        String id = "1";
        MultipartFile multipartFile = new MockMultipartFile("test",
                "test.txt",
                "text/plain",
                "Testing purposes".getBytes());

        Recipe recipe = new Recipe();
        recipe.setId(id);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));
        when(recipeReactiveRepository.save(any(Recipe.class))).thenReturn(Mono.just(recipe));

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        // when
        imageService.saveImageFile(id, multipartFile);

        // then
        verify(recipeReactiveRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);

    }
}