package me.tamarazolotovskaya.recipeseverydayapp.controllers;

import me.tamarazolotovskaya.recipeseverydayapp.model.Ingredient;
import me.tamarazolotovskaya.recipeseverydayapp.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/ingredient")
@RestController
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Integer> addIngredient(@RequestBody Ingredient ingredient) {
        int id = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        if (ingredient != null) {
            return ResponseEntity.ok().body(ingredient);
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Ingredient>> getAllIngredient() {
        ArrayList<Ingredient> allIngredients = ingredientService.getAllIngredient();
        if (allIngredients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allIngredients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        ingredient = ingredientService.editIngredient(id, ingredient);
        if (ingredient != null) {
            return ResponseEntity.ok().body(ingredient);
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }


}
