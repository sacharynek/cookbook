package javastart.cookbook.model.recipeingredient;

import javastart.cookbook.model.ingredient.Ingredient;
import javastart.cookbook.model.ingredient.Unit;
import javastart.cookbook.model.recipe.Recipe;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private int amount;

}
