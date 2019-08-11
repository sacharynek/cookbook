package javastart.cookbook.controller;

import javastart.cookbook.model.recipe.Recipe;
import javastart.cookbook.model.recipeingredient.RecipeIngredient;
import javastart.cookbook.model.recipeingredient.Unit;
import javastart.cookbook.repository.IngredientRepository;
import javastart.cookbook.repository.RecipeIngredientRepository;
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
    IngredientRepository ingredientRepository;
    RecipeIngredientRepository recipeIngredientRepository;


    public RecipeController(RecipeRepository recipeRepository,
            IngredientRepository ingredientRepository,
            RecipeIngredientRepository recipeIngredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
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
    public String addRecipe(HttpServletRequest request, Model model) {

        Recipe recipe = new Recipe();
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(recipe);
        recipe.setDraft(true);

        model.addAttribute("recipe", recipe);
        model.addAttribute("recipeIngredient", recipeIngredient );
        model.addAttribute("units", Unit.values());
        model.addAttribute("existingIngredients", ingredientRepository.findAll());

        model.addAttribute("breadcrumbs", produceBreadcrumbs(request));




        return "recipe/addRecipeForm";
    }

    //todo tutaj potrzebny ejst parametr który mówi, gdzie ma przekierowywać
    @PostMapping("/add")
    public String saveRecipe(HttpServletRequest request, Recipe recipe, RecipeIngredient recipeIngredient, @RequestParam(value = "redirect", required = false) boolean redirect) {

        recipeRepository.save(recipe);
        recipeIngredientRepository.save(recipeIngredient);

        if (redirect && recipe.isDraft()) {
            return "redirect:/recipes/" + recipe.getId()+"/edit";

        }

        return "redirect:/recipes/";
    }

    @GetMapping("/{id}/edit")
    public String editChosenIngriedient(HttpServletRequest request, Model model, @PathVariable(value = "id") Long id) {

        model.addAttribute("breadcrumbs", produceBreadcrumbs(request));

        RecipeIngredient recipeIngredient = new RecipeIngredient();


        Optional<Recipe> recipeOption = recipeRepository.findById(id);

        if (recipeOption.isPresent()) {
            Recipe recipe = recipeOption.get();
            model.addAttribute("recipe", recipe);
            recipeIngredient.setRecipe(recipe);
            model.addAttribute("recipeIngredient", recipeIngredient);
            model.addAttribute("existingIngredients", ingredientRepository.findAll());
            model.addAttribute("units", Unit.values());

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
