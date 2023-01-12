package me.tamarazolotovskaya.recipeseverydayapp.services;

import me.tamarazolotovskaya.recipeseverydayapp.model.Ingredient;
import me.tamarazolotovskaya.recipeseverydayapp.model.Recipe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ValidateService {

    public boolean isNotValid(Recipe recipe) {
        boolean result = StringUtils.isBlank(recipe.getTitle()) ||
                CollectionUtils.isEmpty(recipe.getIngredients()) ||
                CollectionUtils.isEmpty(recipe.getSteps()) ||
                recipe.getCookingTime() < 0;
        if (!result) {
            for (Ingredient ingredient :
                    recipe.getIngredients()) {
                result = result || isNotvalid(ingredient);
            }
        }
        return result;
    }

    public boolean isNotvalid(Ingredient ingredient) {
        return StringUtils.isBlank(ingredient.getTitle()) ||
                StringUtils.isBlank(ingredient.getUnit()) ||
                ingredient.getQuantity() < 0;
    }
}
