package recipes.database.recipe;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Recipe> getRecipeByCategory(String category) {
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> getBySpecifiedName(String name) {
        return recipeRepository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }
}
