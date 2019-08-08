package javastart.cookbook.model.cookbook;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CookBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Category category;//zrobić mozliwośc dodawania nowych kategorii

    //private List<Recipe> recipeList;
}
