package javastart.cookbook.controller;

import javastart.cookbook.model.ingredient.Ingredient;
import javastart.cookbook.model.recipeingredient.Unit;
import javastart.cookbook.repository.IngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RequestMapping("/ingredients")
@Controller
public class IngredientController extends AbstractController {


    private IngredientRepository ingredientRepository;

    public IngredientController(HttpServletRequest request, IngredientRepository ingredientRepository) {

        this.ingredientRepository = ingredientRepository;
    }

    //Wyświetla wszystkie składniki
    @GetMapping("/")
    public String getAllIngredients(HttpServletRequest request, Model model) {
        List<String> breadcrumbs =  produceBreadcrumbs(request);
        model.addAttribute("breadcrumbs", breadcrumbs);
        List<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients", ingredients);

        return "ingredient/ingredients";
    }


    //wyświetla wybrany składnik
    @GetMapping("/{id}")
    public String getIngriedientById(HttpServletRequest request, Model model, @PathVariable(value = "id") Long id) {
        List<String> breadcrumbs =  produceBreadcrumbs(request);
        model.addAttribute("breadcrumbs", breadcrumbs);
        Optional<Ingredient> ingredientOption = ingredientRepository.findById(id);

        if (ingredientOption.isPresent()) {
            Ingredient ingredient = ingredientOption.get();
            model.addAttribute("ingredient", ingredient);
            return "ingredient/ingredientDetails";
        } else {
            return "notFound";
        }

    }

    //przekierowuje na formularz do dodania nowego składnika
    @GetMapping("/add")
    public String editChosenIngriedient(HttpServletRequest request, Model model) {
        List<String> breadcrumbs =  produceBreadcrumbs(request);
        model.addAttribute("breadcrumbs", breadcrumbs);
//        model.addAttribute("ingredientUnits", Unit.values());
        return "ingredient/addIngredientForm";
    }

    //dodaje nowy składnik do bazy danych
    @PostMapping("/add")
    public String editChosenIngriedient(HttpServletRequest request, Ingredient ingredient) {

        ingredientRepository.save(ingredient);

        return "redirect:/ingredients/";
    }

    @GetMapping("/{id}/edit")
    public String editChosenIngriedient(HttpServletRequest request, Model model, @PathVariable(value = "id") Long id) {
        List<String> breadcrumbs =  produceBreadcrumbs(request);
        model.addAttribute("breadcrumbs", breadcrumbs);
        Optional<Ingredient> ingredientOption = ingredientRepository.findById(id);

        if (ingredientOption.isPresent()) {
            Ingredient ingredient = ingredientOption.get();
            model.addAttribute("ingredient", ingredient);
            model.addAttribute("units", Unit.values());
            return "ingredient/editIngredientForm";
        } else {
            return "notFound";
        }

    }

    @GetMapping("/{id}/delete")
    public String deleteChosenIngredient(HttpServletRequest request, @PathVariable(value = "id") Long id) {

        Optional<Ingredient> ingredientOption = ingredientRepository.findById(id);

        if (ingredientOption.isPresent()) {
            Ingredient ingredient = ingredientOption.get();
            ingredientRepository.delete(ingredient);
            return "redirect:/ingredients/";
        } else {
            return "notFound";
        }

    }

}
