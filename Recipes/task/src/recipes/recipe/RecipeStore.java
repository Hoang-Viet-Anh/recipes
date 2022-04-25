package recipes.recipe;

import java.util.concurrent.ConcurrentHashMap;

public class RecipeStore {
    public ConcurrentHashMap<Long, Recipe> recipes = new ConcurrentHashMap<>();

    private static final RecipeStore INSTANCE = new RecipeStore();

    private RecipeStore() {}

    public static RecipeStore getInstance() {
        return INSTANCE;
    }
}
