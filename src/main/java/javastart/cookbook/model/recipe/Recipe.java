package javastart.cookbook.model.recipe;

import javastart.cookbook.model.ingredient.Ingredient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


   // private Map<Ingredient, Integer> ingredientAmountMap; //ingredient and amount

    private String instruction;

    private String photo;

}
