package javastart.cookbook.model.recipeingredient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/recipeingredient")
public class RecipeIngredientController {

    RecipeIngredientRepository recipeIngredientRepository;

    public RecipeIngredientController(RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
    }

    @GetMapping("/")
    public String getAllRecipes(Model model) {

        model.addAttribute("recipeIngredients", recipeIngredientRepository.findAll());

        return "recipeIngredient/recipeIngredients";
    }

    //todo
    @GetMapping("/{id}")
    public String getRecipeById(Model model, @PathVariable(value = "id") Long id) {

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
    public String editChosenIngriedient(Model model) {

        return "recipeIngredient/addRecipeIngredientForm";
    }

    //todo
    //dodaje nowy składnik do bazy danych
    @PostMapping("/add")
    public String editChosenRecipeIngriedient(RecipeIngredient recipeIngredient) {

        recipeIngredientRepository.save(recipeIngredient);

        return "redirect:/recipesIngredient/";
    }

    //todo
    @GetMapping("/{id}/edit")
    public String editChosenIngriedient(Model model, @PathVariable(value = "id") Long id) {

        Optional<RecipeIngredient> recipeIngredientOptional = recipeIngredientRepository.findById(id);

        if (recipeIngredientOptional.isPresent()) {
            RecipeIngredient recipeIngredient = recipeIngredientOptional.get();
            model.addAttribute("recipeIngredient", recipeIngredient);

            return "recipeIngredient/editRecipeIngredientForm";
        } else {
            return "notFound";
        }

    }


    //todo
    @GetMapping("/{id}/delete")
    public String deleteChosenIngredient(@PathVariable(value = "id") Long id) {

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
