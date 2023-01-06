package me.tamarazolotovskaya.recipeseverydayapp.services;

import me.tamarazolotovskaya.recipeseverydayapp.model.Ingredient;
import org.springframework.stereotype.Service;


public interface IngredientService {


    int addIngredient(Ingredient ingredient);


    Ingredient getIngredient(int id);
}
