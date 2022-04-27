package recipes.database.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.*;
import recipes.database.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "description")
    private String description;

    @NotEmpty
    @Convert(converter = ListToStringConverter.class)
    @Column(name = "ingredients")
    private List<String> ingredients;

    @NotEmpty
    @Convert(converter = ListToStringConverter.class)
    @Column(name = "directions")
    private List<String> directions;

    @NotBlank
    @Column(name = "category")
    private String category;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public JsonObject toJsonObject() {
        JsonObject response = new JsonObject();
        response.addProperty("name", getName());
        response.addProperty("category", getCategory());
        response.addProperty("date", getDate().toString());
        response.addProperty("description", getDescription());
        JsonArray array = new JsonArray();
        getIngredients().forEach(array::add);
        response.add("ingredients", array);
        array = new JsonArray();
        getDirections().forEach(array::add);
        response.add("directions", array);
        return response;
    }
}
