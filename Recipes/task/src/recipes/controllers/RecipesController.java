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
import java.util.Map;

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
            return new ResponseEntity<>(gson.toJson(recipe.toJsonObject()), HttpStatus.OK);
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

    @PutMapping("/api/recipe/{id}")
    ResponseEntity<String> updateRecipe(@PathVariable long id, @Valid @RequestBody Recipe recipe, Errors errors) {
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldError().getDefaultMessage());
        } else if (!recipeService.recipeExistsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe with specified id not found.");
        } else {
            recipe.setId(id);
            recipeService.saveRecipe(recipe);
            JsonObject object = new JsonObject();
            object.addProperty("status", "success!");
            return new ResponseEntity<>(gson.toJson(object), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/api/recipe/search")
    ResponseEntity<String> getSpecifiedRecipes(@RequestParam Map<String, String> params) {
        if (params.size() != 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only one parameter should be passed.");
        } else if (!(params.containsKey("category") || params.containsKey("name"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong parameters.");
        } else {
            JsonArray array = new JsonArray();
            if (params.containsKey("category")) {
                recipeService.getRecipeByCategory(params.get("category"))
                        .forEach(a -> array.add(a.toJsonObject()));
            } else if (params.containsKey("name")) {
                recipeService.getBySpecifiedName(params.get("name"))
                        .forEach(a -> array.add(a.toJsonObject()));
            }
            return new ResponseEntity<>(gson.toJson(array), HttpStatus.OK);
        }
    }
}
