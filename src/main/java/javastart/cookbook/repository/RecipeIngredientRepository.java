package javastart.cookbook.repository;

import javastart.cookbook.model.recipeingredient.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {

}
