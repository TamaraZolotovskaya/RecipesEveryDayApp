package me.tamarazolotovskaya.recipeseverydayapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

   @GetMapping
    public String helloWord(){
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String info()
    {
        return "Разработчик: Тамара Золотовская <br>" +
                "Название проекта: Recipes Every Day App<br>" +
                "Дата создания проекта: 01.01.2023<br>" +
                "Описание проекта: Приложение для сайта рецептов<br>";
    }


}
