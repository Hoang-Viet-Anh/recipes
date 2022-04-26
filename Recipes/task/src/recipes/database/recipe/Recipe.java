package recipes.database.recipe;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
}
