package me.tamarazolotovskaya.recipeseverydayapp.services;

import me.tamarazolotovskaya.recipeseverydayapp.model.Recipe;

public interface RecipeService {

    int addRecipe(Recipe ingredient);

    Recipe getRecipe(int id);
}
