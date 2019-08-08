package javastart.cookbook.model.recipe;

import javastart.cookbook.model.recipeingredient.RecipeIngredient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String instruction;

    private String photo;//tymczasowo przechowujmy link do foto

    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredient> recipeIngredient;

    private boolean isDraft;
    private LocalDateTime draftCreationDateTime;


}
