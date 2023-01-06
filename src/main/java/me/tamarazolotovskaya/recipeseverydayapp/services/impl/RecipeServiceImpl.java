package me.tamarazolotovskaya.recipeseverydayapp.services.impl;

import me.tamarazolotovskaya.recipeseverydayapp.model.Recipe;
import me.tamarazolotovskaya.recipeseverydayapp.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static int recipeId = 0;

    @Override
    public int addRecipe(Recipe ingredient) {
        recipeMap.put(++recipeId, ingredient);
        return recipeId;
    }

    @Override
    public Recipe getRecipe(int id) {
        Recipe recipe = recipeMap.get(id);
        return recipe;
    }

}
