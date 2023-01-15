package me.tamarazolotovskaya.recipeseverydayapp.services;

import java.io.File;

public interface FileService {


    void saveToJsonFile(Object object, String fileName);

    String readFromFile(String fileName);


}
