package javastart.cookbook.model.recipe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RequestMapping("/recipes")
@Controller
public class RecipeController {

    RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/")
    public String getAllRecipes(Model model) {

        model.addAttribute("recipes", recipeRepository.findAll());

        return "recipe/recipes";
    }

    @GetMapping("/{id}")
    public String getRecipeById(Model model, @PathVariable(value = "id") Long id) {

        Optional<Recipe> recipeOption = recipeRepository.findById(id);

        if (recipeOption.isPresent()) {
            Recipe recipe = recipeOption.get();
            model.addAttribute("recipe", recipe);
            return "recipe/recipeDetails";
        } else {
            return "notFound";
        }

    }


    //przekierowuje na formularz do dodania nowego składnika
    @GetMapping("/add")
    public String editChosenIngriedient(Model model) {

        return "recipe/addRecipeForm";
    }

    //dodaje nowy składnik do bazy danych
    @PostMapping("/add")
    public String editChosenIngriedient(Recipe recipe) {

        recipeRepository.save(recipe);

        return "redirect:/recipes/";
    }

    @GetMapping("/{id}/edit")
    public String editChosenIngriedient(Model model, @PathVariable(value = "id") Long id) {

        Optional<Recipe> recipeOption = recipeRepository.findById(id);

        if (recipeOption.isPresent()) {
            Recipe recipe = recipeOption.get();
            model.addAttribute("recipe", recipe);

            return "recipe/editRecipeForm";
        } else {
            return "notFound";
        }

    }

    @GetMapping("/{id}/delete")
    public String deleteChosenIngredient(@PathVariable(value = "id") Long id) {

        Optional<Recipe> recipeOption = recipeRepository.findById(id);

        if (recipeOption.isPresent()) {
            Recipe recipe = recipeOption.get();
            recipeRepository.delete(recipe);
            return "redirect:/recipes/";
        } else {
            return "notFound";
        }

    }

}
