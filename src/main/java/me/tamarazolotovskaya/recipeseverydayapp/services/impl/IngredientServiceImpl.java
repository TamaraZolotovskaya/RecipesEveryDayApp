package me.tamarazolotovskaya.recipeseverydayapp.services.impl;
import me.tamarazolotovskaya.recipeseverydayapp.model.Ingredient;
import me.tamarazolotovskaya.recipeseverydayapp.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static int ingredientId = 0;


    @Override
    public int addIngredient(Ingredient ingredient) {
        ingredientMap.put(++ingredientId, ingredient);
        return ingredientId;
    }

    @Override
    public Ingredient getIngredient(int id) {
        Ingredient ingredient = ingredientMap.get(id);
        return ingredient;
    }

}
