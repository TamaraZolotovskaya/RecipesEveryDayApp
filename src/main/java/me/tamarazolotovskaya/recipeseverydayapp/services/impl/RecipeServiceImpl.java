package me.tamarazolotovskaya.recipeseverydayapp.services.impl;

import me.tamarazolotovskaya.recipeseverydayapp.model.Ingredient;
import me.tamarazolotovskaya.recipeseverydayapp.model.Recipe;
import me.tamarazolotovskaya.recipeseverydayapp.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.tamarazolotovskaya.recipeseverydayapp.services.impl.IngredientServiceImpl.ingredientMap;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static int recipeId = 0;

    @Override
    public int addRecipe(Recipe recipe) {
        recipeMap.put(++recipeId, recipe);
        return recipeId;
    }

    @Override
    public Recipe getRecipe(int id) {
        Recipe recipe = recipeMap.get(id);
        return recipe;
    }

    @Override
    public ArrayList<Recipe> getAllRecipe() {
        Collection<Recipe> recipeCollection = recipeMap.values();
        ArrayList<Recipe> recipeArrayList = new ArrayList<>(recipeCollection);
        return recipeArrayList;
    }

    @Override
    public Recipe editRecipe(int id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            return recipe;
        }
        return null;
    }

    @Override
    public boolean deleteRecipe(int id) {
        if (recipeMap.containsKey(id)) {
            recipeMap.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Recipe> getRecipeByIngredient(int indredientId) {
        ArrayList<Recipe> recipeWithIngredient = new ArrayList<>();
        Ingredient ingredient = ingredientMap.get(indredientId);
        for (Recipe recipe :
                recipeMap.values()) {
            List<Ingredient> ingredientList = recipe.getIngredients();
            for (Ingredient recipeIngredient :
                    ingredientList) {
                if (recipeIngredient.equals(ingredient)) {
                    recipeWithIngredient.add(recipe);
                }
            }
        }
        return recipeWithIngredient;
    }

    @Override
    public ArrayList<Recipe> getRecipeByIngredientList(ArrayList<String> ingredients) {
        ArrayList<Recipe> recipeWithIngredients = new ArrayList<>();
        for (Recipe recipe :
                recipeMap.values()) {
            List<Ingredient> ingredientList = recipe.getIngredients();
            List<String> ingredientTitleList = new ArrayList<>();
            for (Ingredient ingredient :
                    ingredientList) {
                ingredientTitleList.add(ingredient.getTitle());
            }
            if (ingredientTitleList.containsAll(ingredients)) {
                recipeWithIngredients.add(recipe);
            }
        }
        return recipeWithIngredients;
    }

}
