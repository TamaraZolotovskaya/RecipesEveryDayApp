package me.tamarazolotovskaya.recipeseverydayapp.controllers;
import me.tamarazolotovskaya.recipeseverydayapp.model.Recipe;
import me.tamarazolotovskaya.recipeseverydayapp.services.RecipeService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/recipe")
@RestController
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public String addRecipe(@RequestBody Recipe recipe) {
        int id = recipeService.addRecipe(recipe);
        return ("Добавлен ингредиент " + recipe + " с номером " + id);
    }

    @GetMapping("/get/{id}")
    public String getRecipe(@PathVariable int id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe != null) {
            return ("Ингредиент с номером " + id + ": " + recipe);
        } else return ("Ингредиента с номером " + id + " не существует");
    }


}
