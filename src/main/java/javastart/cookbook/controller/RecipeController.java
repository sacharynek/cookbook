package javastart.cookbook.controller;

import javastart.cookbook.model.recipe.Recipe;
import javastart.cookbook.repository.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
        List<String> breadcrumbs = produceBreadcrumbs(request);
        model.addAttribute("breadcrumbs", breadcrumbs);
        model.addAttribute("recipes", recipeRepository.findAll());

        return "recipe/recipes";
    }

    @GetMapping("/{id}")
    public String getRecipeById(HttpServletRequest request, Model model, @PathVariable(value = "id") Long id) {
        List<String> breadcrumbs = produceBreadcrumbs(request);
        model.addAttribute("breadcrumbs", breadcrumbs);
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
        List<String> breadcrumbs = produceBreadcrumbs(request);
        model.addAttribute("breadcrumbs", breadcrumbs);
        return "recipe/addRecipeForm";
    }

    //dodaje nowy składnik do bazy danych
    @PostMapping("/add")
    public String editChosenIngriedient(HttpServletRequest request, Recipe recipe) {

        recipeRepository.save(recipe);

        return "redirect:/recipes/";
    }

    @GetMapping("/{id}/edit")
    public String editChosenIngriedient(HttpServletRequest request, Model model, @PathVariable(value = "id") Long id) {
        List<String> breadcrumbs = produceBreadcrumbs(request);
        model.addAttribute("breadcrumbs", breadcrumbs);
        Optional<Recipe> recipeOption = recipeRepository.findById(id);

        if (recipeOption.isPresent()) {
            Recipe recipe = recipeOption.get();
            model.addAttribute("recipe", recipe);

            return "recipe/editRecipeForm";
        } else {
            return "notFound";
        }

    }

    @GetMapping("/{id}/like")
    public String addLike(HttpServletRequest request, Model model, @PathVariable(value = "id") Long id, @RequestParam(value = "redirect", required = false) boolean redirect) {

        Optional<Recipe> recipeOption = recipeRepository.findById(id);

        if (recipeOption.isPresent()) {
            Recipe recipe = recipeOption.get();
            recipe.setLikeCounter(recipe.getLikeCounter() + 1);
            recipeRepository.save(recipe);
            if (redirect) {
                return "redirect:/recipes/";
            } else {
                return "redirect:/recipes/" + id;
            }

        } else {
            return "notFound";
        }

    }

    @GetMapping("/{id}/dislike")
    public String removeLike(HttpServletRequest request, Model model, @PathVariable(value = "id") Long id, @RequestParam(value = "redirect", required = false) boolean redirect) {
        System.out.println(request.getRequestURL());
        Optional<Recipe> recipeOption = recipeRepository.findById(id);

        if (recipeOption.isPresent()) {
            Recipe recipe = recipeOption.get();
            recipe.setLikeCounter(recipe.getLikeCounter() - 1);
            recipeRepository.save(recipe);
            if (redirect) {
                return "redirect:/recipes/";
            } else {
                return "redirect:/recipes/" + id;
            }
        } else {
            return "notFound";
        }

    }

    @GetMapping("/{id}/delete")
    public String deleteChosenIngredient(HttpServletRequest request, @PathVariable(value = "id") Long id) {

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
