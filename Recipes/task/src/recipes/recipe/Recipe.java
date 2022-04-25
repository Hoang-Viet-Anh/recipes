package recipes.recipe;

import lombok.*;

import javax.validation.constraints.NotEmpty;

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
    @NotEmpty
    private String ingredients;
    @NotEmpty
    private String directions;
}
