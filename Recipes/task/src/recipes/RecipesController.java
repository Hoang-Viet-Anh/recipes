package recipes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import recipes.recipe.Recipe;
import javax.validation.Valid;

@RestController
public class RecipesController {
    @Autowired
    Gson gson;

    private Recipe recipe = null;

    @PostMapping("/api/recipe")
    ResponseEntity<String> setRecipe(@Valid @RequestBody Recipe recipe, Errors errors) {
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more fields is empty.");
        }
        this.recipe = recipe.toBuilder().build();
        JsonObject response = new JsonObject();
        response.addProperty("status", "Recipe changed!");
        return new ResponseEntity<>(gson.toJson(response), HttpStatus.OK);
    }

    @GetMapping("/api/recipe")
    ResponseEntity<String> getRecipe() {
        return new ResponseEntity<>(gson.toJson(recipe == null ? new JsonObject() : recipe),
                HttpStatus.OK);
    }
}
