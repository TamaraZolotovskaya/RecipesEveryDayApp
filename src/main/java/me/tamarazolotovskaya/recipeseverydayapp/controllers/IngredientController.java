package me.tamarazolotovskaya.recipeseverydayapp.controllers;
import me.tamarazolotovskaya.recipeseverydayapp.model.Ingredient;
import me.tamarazolotovskaya.recipeseverydayapp.services.IngredientService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ingredient")
@RestController
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public String addIngredient(@RequestBody Ingredient ingredient) {
        int id = ingredientService.addIngredient(ingredient);
        return ("Добавлен ингредиент " + ingredient + " с номером " + id);
    }

    @GetMapping("/get/{id}")
    public String getIngredient(@PathVariable int id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        if (ingredient != null) {
            return ("Ингредиент с номером " + id + ": " + ingredient);
        } else return ("Ингредиента с номером " + id + " не существует");
    }


}
