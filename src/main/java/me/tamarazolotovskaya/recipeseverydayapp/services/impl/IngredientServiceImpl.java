package me.tamarazolotovskaya.recipeseverydayapp.services.impl;

import me.tamarazolotovskaya.recipeseverydayapp.model.Ingredient;
import me.tamarazolotovskaya.recipeseverydayapp.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {
    public static Map<Integer, Ingredient> ingredientMap = new HashMap<>();
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

    @Override
    public ArrayList<Ingredient> getAllIngredient() {
        Collection<Ingredient> ingredientCollection = ingredientMap.values();
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>(ingredientCollection);
        return ingredientArrayList;
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.put(id, ingredient);
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(int id) {
        if (ingredientMap.containsKey(id)) {
            ingredientMap.remove(id);
            return true;
        }
        return false;
    }

}
