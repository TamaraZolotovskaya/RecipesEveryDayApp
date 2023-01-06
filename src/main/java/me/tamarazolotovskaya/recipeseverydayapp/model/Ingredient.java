package me.tamarazolotovskaya.recipeseverydayapp.model;

import lombok.*;

@Data
public class Ingredient {
    @NonNull private String title;
private int quantity;
private String unit;

    public void setDuration(int quantity) {
        if(quantity<0){quantity=Math.abs(quantity);}
        this.quantity = quantity;
    }

    public Ingredient(@NonNull String title, int quantity, String unit) {
        this.title = title;
        setDuration(quantity);
        this.unit = unit;
    }
}
