package recipes.database.recipe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    boolean existsById(long id);
    boolean existsByNameAndDescription(String name, String description);
    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);
    List<Recipe> findByNameContainingIgnoreCaseOrderByDateDesc(String name);
}
