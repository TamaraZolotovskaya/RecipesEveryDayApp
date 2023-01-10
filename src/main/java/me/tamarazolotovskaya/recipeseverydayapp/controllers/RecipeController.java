package me.tamarazolotovskaya.recipeseverydayapp.controllers;

import me.tamarazolotovskaya.recipeseverydayapp.model.Ingredient;
import me.tamarazolotovskaya.recipeseverydayapp.model.Recipe;
import me.tamarazolotovskaya.recipeseverydayapp.model.Recipe;
import me.tamarazolotovskaya.recipeseverydayapp.model.Recipe;
import me.tamarazolotovskaya.recipeseverydayapp.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/recipe")
@RestController
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Integer> addRecipe(@RequestBody Recipe recipe) {
        int id = recipeService.addRecipe(recipe);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe != null) {
            return ResponseEntity.ok().body(recipe);
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Recipe>> getAllRecipe() {
        ArrayList<Recipe> allRecipes = recipeService.getAllRecipe();
        if (allRecipes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allRecipes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        recipe = recipeService.editRecipe(id, recipe);
        if (recipe != null) {
            return ResponseEntity.ok().body(recipe);
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping("/ingredient/{id}")
    public ResponseEntity<ArrayList<Recipe>> getRecipeByIngredient(@PathVariable int id) {
        ArrayList<Recipe> recipeWithIngredient = recipeService.getRecipeByIngredient(id);
        if (recipeWithIngredient.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok().body(recipeWithIngredient);
    }

    @PutMapping("/ingredients")
    public ResponseEntity<ArrayList<Recipe>>getRecipeByIngredientList
            (@RequestBody ArrayList<String> ingredients){
        ArrayList<Recipe> recipeWithIngredients = recipeService.getRecipeByIngredientList(ingredients);
        if (recipeWithIngredients.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok().body(recipeWithIngredients);
    }


}
