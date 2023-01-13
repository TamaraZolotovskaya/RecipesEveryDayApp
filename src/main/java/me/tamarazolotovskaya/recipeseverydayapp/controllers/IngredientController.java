package me.tamarazolotovskaya.recipeseverydayapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.tamarazolotovskaya.recipeseverydayapp.model.Ingredient;
import me.tamarazolotovskaya.recipeseverydayapp.services.IngredientService;
import me.tamarazolotovskaya.recipeseverydayapp.services.ValidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/ingredient")
@RestController
@Tag(name = "Ингредиенты", description = "API для работы с ингредиентами")
public class IngredientController {

    private final IngredientService ingredientService;
    private final ValidateService validateService;

    public IngredientController(IngredientService ingredientService, ValidateService validateService) {
        this.ingredientService = ingredientService;
        this.validateService = validateService;
    }

    @PostMapping
    @Operation(
            summary = "Добавление ингредиента"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент добавлен"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Некорректные параметры ингредиента"
                    )
            }
    )
    public ResponseEntity<Integer> addIngredient(@RequestBody Ingredient ingredient) {
        if (validateService.isNotvalid(ingredient)){
            return ResponseEntity.badRequest().build();
        }
        int id = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение ингредиента по id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент найден"

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент с таким id не найден"
                    )
            }
    )
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id) {
        Ingredient ingredient = ingredientService.getIngredient(id);
        if (ingredient != null) {
            return ResponseEntity.ok().body(ingredient);
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(
            summary = "Получение списка всех ингредиентов"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список ингредиентов получен"

                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Не одного ингредиента ни найдено"
                    )
            }
    )
    public ResponseEntity<ArrayList<Ingredient>> getAllIngredient() {
        ArrayList<Ingredient> allIngredients = ingredientService.getAllIngredient();
        if (allIngredients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allIngredients);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Редактирование ингредиента по id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент отредактирован"

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент с таким id не найден"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Некорректные параметры ингредиента"
                    )
            }
    )
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient) {
        if (validateService.isNotvalid(ingredient)){
            return ResponseEntity.badRequest().build();
        }
        ingredient = ingredientService.editIngredient(id, ingredient);
        if (ingredient != null) {
            return ResponseEntity.ok().body(ingredient);
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиента по id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ингредиент удалён"

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ингредиент с таким id не найден"
                    )
            }
    )
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        } else return ResponseEntity.notFound().build();
    }


}
