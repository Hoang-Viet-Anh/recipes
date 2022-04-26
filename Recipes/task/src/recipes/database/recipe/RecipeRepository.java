package recipes.database.recipe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    boolean existsById(long id);
    boolean existsByNameAndDescription(String name, String description);
}
