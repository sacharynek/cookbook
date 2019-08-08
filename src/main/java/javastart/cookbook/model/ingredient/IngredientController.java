package javastart.cookbook.model.ingredient;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping("/ingredients")
@Controller
public class IngredientController {


    private IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository ingredientRepository) {

        this.ingredientRepository = ingredientRepository;
    }

    //Wyświetla wszystkie składniki
    @GetMapping("/")
    public String getAllIngredients(Model model) {

        List<Ingredient> ingredients = ingredientRepository.findAll();
        model.addAttribute("ingredients", ingredients);

        return "ingredient/ingredients";
    }


    //wyświetla wybrany składnik
    @GetMapping("/{id}")
    public String getIngriedientById(Model model, @PathVariable(value = "id") Long id) {

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
    public String editChosenIngriedient(Model model) {

        return "ingredient/addIngredientForm";
    }

    //dodaje nowy składnik do bazy danych
    @PostMapping("/add")
    public String editChosenIngriedient(Ingredient ingredient) {

        ingredientRepository.save(ingredient);

        return "redirect:/ingredients/";
    }

    @GetMapping("/{id}/edit")
    public String editChosenIngriedient(Model model, @PathVariable(value = "id") Long id) {

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
    public String deleteChosenIngredient(@PathVariable(value = "id") Long id) {

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
