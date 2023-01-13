package me.tamarazolotovskaya.recipeseverydayapp.services;

public interface FileService {


    void saveToJsonFile(Object object, String fileName);

    String readFromFile(String fileName);
}
