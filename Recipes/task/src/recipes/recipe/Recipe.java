package recipes.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Recipe {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private List<String> ingredients;
    @NotNull
    private List<String> directions;
}
