package javastart.cookbook.controller;

import javastart.cookbook.model.recipe.Recipe;
import javastart.cookbook.repository.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequestMapping("/recipes")
@Controller
public class RecipeController extends AbstractController {

    RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/")
    public String getAllRecipes(HttpServletRequest request, Model model) {
        System.out.println(request.getRequestURL());
        model.addAttribute("recipes", recipeRepository.findAll());

        return "recipe/recipes";
    }

    @GetMapping("/{id}")
    public String getRecipeById(HttpServletRequest request, Model model, @PathVariable(value = "id") Long id) {
        System.out.println(request.getRequestURL());
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
    public String editChosenIngriedient(HttpServletRequest request, Model model) {
        System.out.println(request.getRequestURL());
        return "recipe/addRecipeForm";
    }

    //dodaje nowy składnik do bazy danych
    @PostMapping("/add")
    public String editChosenIngriedient(HttpServletRequest request, Recipe recipe) {
        System.out.println(request.getRequestURL());
        recipeRepository.save(recipe);

        return "redirect:/recipes/";
    }

    @GetMapping("/{id}/edit")
    public String editChosenIngriedient(HttpServletRequest request, Model model, @PathVariable(value = "id") Long id) {
        System.out.println(request.getRequestURL());
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
    public String deleteChosenIngredient(HttpServletRequest request, @PathVariable(value = "id") Long id) {
        System.out.println(request.getRequestURL());
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
