package recipes.database.recipe;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RecipeService {
    @Autowired
    private final RecipeRepository recipeRepository;

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public boolean recipeExistsById(long id) {
        return recipeRepository.existsById(id);
    }

    public Recipe getRecipeById(long id) {
        return recipeRepository.findById(id).get();
    }

    public void deleteRecipeById(long id) {
        recipeRepository.deleteById(id);
    }

    public boolean existsByNameAndDesc(String name, String desc) {
        return recipeRepository.existsByNameAndDescription(name, desc);
    }
}
