package me.tamarazolotovskaya.recipeseverydayapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.tamarazolotovskaya.recipeseverydayapp.services.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
@Tag(name = "Передача файлов по HTTP", description = "API для работы с файлами")
public class FilesController {
    @Value("${path.to.data.file}")
    private String dataFilepath;
    @Value("${name.of.recipes.data.file}")
    private String recipeDataFileName;

    @Value("${name.of.ingredients.data.file}")
    private String ingredientDataFileName;

    @Operation(
            summary = "Скачать все рецепты в виде json-файла"
    )
    @GetMapping(value = "/recipes/download")
    public ResponseEntity<InputStreamResource> downloadRecipesFile() throws IOException {
        File file = new File(dataFilepath + "/" + recipeDataFileName);
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + recipeDataFileName)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Принимает json-файл с рецептами и заменяет сохраненный на жестком диске файл на новый"
    )
    @PostMapping(value = "/recipes/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipesFile(@RequestParam MultipartFile file)  {
        Path filePath = Path.of(dataFilepath,recipeDataFileName);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File newFile = new File(dataFilepath + "/" + recipeDataFileName);
        try(FileOutputStream fos = new FileOutputStream(newFile)){
            IOUtils.copy(file.getInputStream(),fos);
            return ResponseEntity.ok().build();
        }
         catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Operation(
            summary = "Принимает json-файл с ингредиентами и заменяет сохраненный на жестком диске файл на новый"
    )
    @PostMapping(value = "/ingredients/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadIngredientsFile(@RequestParam MultipartFile file)  {
        Path filePath = Path.of(dataFilepath,file.getOriginalFilename());
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File newFile = new File(dataFilepath + "/" + ingredientDataFileName);
        try(FileOutputStream fos = new FileOutputStream(newFile)){
            IOUtils.copy(file.getInputStream(),fos);
            return ResponseEntity.ok().build();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
