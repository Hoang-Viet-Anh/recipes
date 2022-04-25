package recipes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.recipe.Recipe;
import recipes.recipe.RecipeStore;

import javax.validation.Valid;

@RestController
public class RecipesController {
    @Autowired
    Gson gson;

    private long numberOfRecipes = 1L;

    private RecipeStore recipeStore = RecipeStore.getInstance();

    @PostMapping("/api/recipe/new")
    ResponseEntity<String> setRecipe(@Valid @RequestBody Recipe recipe, Errors errors) {
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more fields is empty.");
        }
        recipeStore.recipes.put(numberOfRecipes, recipe);
        JsonObject response = new JsonObject();
        response.addProperty("id", numberOfRecipes);
        numberOfRecipes++;
        return new ResponseEntity<>(gson.toJson(response), HttpStatus.OK);
    }

    @GetMapping("/api/recipe/{id}")
    ResponseEntity<String> getRecipe(@PathVariable long id) {
        if (!recipeStore.recipes.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe id not found.");
        } else {
            Recipe recipe = recipeStore.recipes.get(id);
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
}
