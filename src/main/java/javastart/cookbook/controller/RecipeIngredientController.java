package javastart.cookbook.controller;

import javastart.cookbook.model.ingredient.Ingredient;
import javastart.cookbook.model.recipeingredient.RecipeIngredient;
import javastart.cookbook.model.recipeingredient.Unit;
import javastart.cookbook.repository.IngredientRepository;
import javastart.cookbook.repository.RecipeIngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/recipeingredient")
public class RecipeIngredientController extends AbstractController {

    RecipeIngredientRepository recipeIngredientRepository;
    IngredientRepository ingredientRepository;

    public RecipeIngredientController(RecipeIngredientRepository recipeIngredientRepository, IngredientRepository ingredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/")
    public String getAllRecipes(HttpServletRequest request, Model model) {
        List<String> breadcrumbs =  produceBreadcrumbs(request);
        model.addAttribute("breadcrumbs", breadcrumbs);
        produceBreadcrumbs(request);
        model.addAttribute("recipeIngredients", recipeIngredientRepository.findAll());

        return "recipeIngredient/recipeIngredients";
    }

    @GetMapping("/{id}")
    public String getRecipeById(HttpServletRequest request, Model model, @PathVariable(value = "id") Long id) {

        List<String> breadcrumbs =  produceBreadcrumbs(request);
        model.addAttribute("breadcrumbs", breadcrumbs);

        Optional<RecipeIngredient> recipeIngredientOptional = recipeIngredientRepository.findById(id);

        if (recipeIngredientOptional.isPresent()) {
            RecipeIngredient recipeIngredient = recipeIngredientOptional.get();

            model.addAttribute("recipeIngredient", recipeIngredient);
            return "recipeIngredient/recipeIngredientDetails";
        } else {
            return "notFound";
        }
    }

    //todo
    //przekierowuje na formularz do dodania nowego składnika
    @GetMapping("/add")
    public String editChosenIngriedient(HttpServletRequest request, Model model) {
        List<String> breadcrumbs =  produceBreadcrumbs(request);
        model.addAttribute("breadcrumbs", breadcrumbs);

        return "recipeIngredient/addRecipeIngredientForm";
    }

    //dodaje nowy składnik do bazy danych
    @PostMapping("/add")
    public String editChosenRecipeIngriedient(HttpServletRequest request, RecipeIngredient recipeIngredient , @RequestParam(value = "redirect", required = false) boolean redirect) {

        if (!recipeIngredient.equals(null)) {
            recipeIngredientRepository.save(recipeIngredient);
        }

        if (redirect) {
            return "redirect:/recipes/" + recipeIngredient.getRecipe().getId()+"/edit";
        }

        return "redirect:/recipeingredient/";
    }

    //todo
    @GetMapping("/{id}/edit")
    public String editChosenIngriedient(HttpServletRequest request, Model model, @PathVariable(value = "id") Long id) {
        List<String> breadcrumbs =  produceBreadcrumbs(request);
        model.addAttribute("breadcrumbs", breadcrumbs);

        Optional<RecipeIngredient> recipeIngredientOptional = recipeIngredientRepository.findById(id);
        List<Ingredient> ingredients = ingredientRepository.findAll();

        if (recipeIngredientOptional.isPresent()) {
            RecipeIngredient recipeIngredient = recipeIngredientOptional.get();
            model.addAttribute("recipeIngredient", recipeIngredient);
            model.addAttribute("ingredients", ingredients);
            model.addAttribute("Ingredientunits", Unit.values());

            return "recipeIngredient/editRecipeIngredientForm";
        } else {
            return "notFound";
        }

    }

    //todo
    @GetMapping("/{id}/delete")
    public String deleteChosenIngredient(HttpServletRequest request, @PathVariable(value = "id") Long id) {

        Optional<RecipeIngredient> recipeIngredientOptional = recipeIngredientRepository.findById(id);

        if (recipeIngredientOptional.isPresent()) {
            RecipeIngredient recipeIngredient = recipeIngredientOptional.get();
            recipeIngredientRepository.delete(recipeIngredient);
            return "redirect:/recipes/";
        } else {
            return "notFound";
        }

    }


}
