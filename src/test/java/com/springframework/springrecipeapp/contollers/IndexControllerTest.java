package com.springframework.springrecipeapp.contollers;

import com.springframework.springrecipeapp.domain.Recipe;
import com.springframework.springrecipeapp.repsositories.CategoryRepository;
import com.springframework.springrecipeapp.repsositories.RecipeRepository;
import com.springframework.springrecipeapp.repsositories.UnitOfMeasureRepository;
import com.springframework.springrecipeapp.services.RecipeService;
import net.bytebuddy.asm.Advice;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    IndexController indexController;
    @Mock
    Model model;
    @Mock
    RecipeService recipeService;


    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void TestMockMVS() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index.html"));
    }

    @Test
    void indexController_getHome_verify() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setId(2L);
        recipes.add(recipe);
        when(recipeService.findAll()).thenReturn(recipes);
        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        String viewFileName = indexController.getHome(model);

        assertEquals("index.html", viewFileName);
        Mockito.verify(model,times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Mockito.verify(recipeService,times(1)).findAll();
        assertEquals(2, argumentCaptor.getValue().size());
        assertEquals(2L,argumentCaptor.getValue().get(1).getId());
    }
}