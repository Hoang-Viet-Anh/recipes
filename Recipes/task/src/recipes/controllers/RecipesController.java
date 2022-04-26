package recipes.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.database.recipe.Recipe;
import recipes.database.recipe.RecipeService;

import javax.validation.Valid;

@RestController
public class RecipesController {
    @Autowired
    private Gson gson;

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/api/recipe/new")
    ResponseEntity<String> addRecipe(@Valid @RequestBody Recipe recipe, Errors errors) {
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more fields is empty.");
        }
        Recipe addedRecipe = recipeService.saveRecipe(recipe);
        JsonObject response = new JsonObject();
        response.addProperty("id", addedRecipe.getId());
        return new ResponseEntity<>(gson.toJson(response), HttpStatus.OK);
    }

    @GetMapping("/api/recipe/{id}")
    ResponseEntity<String> getRecipe(@PathVariable long id) {
        if (!recipeService.recipeExistsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe with specified id not found.");
        } else {
            Recipe recipe = recipeService.getRecipeById(id);
            JsonObject response = new JsonObject();
            response.addProperty("name", recipe.getName());
            response.addProperty("description", recipe.getDescription());
            JsonArray array = new JsonArray();
            recipe.getIngredients().forEach(array::add);
            response.add("ingredients", array);
            array = new JsonArray();
            recipe.getDirections().forEach(array::add);
            response.add("directions", array);
            return new ResponseEntity<>(gson.toJson(response), HttpStatus.OK);
        }
    }

    @DeleteMapping("/api/recipe/{id}")
    ResponseEntity<String> deteleRecipe(@PathVariable long id) {
        if (!recipeService.recipeExistsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe with specified id not found.");
        }
        recipeService.deleteRecipeById(id);
        JsonObject object = new JsonObject();
        object.addProperty("status", "success!");
        return new ResponseEntity<>(gson.toJson(object), HttpStatus.NO_CONTENT);
    }
}
