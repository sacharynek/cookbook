package javastart.cookbook.model.recipe;

import javastart.cookbook.model.recipeingredient.RecipeIngredient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String instruction;

    @Column(columnDefinition = "TEXT")
    private String photoURL;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredient> recipeIngredient;

    private boolean isDraft;
    private LocalDateTime draftCreationDateTime;

    public Recipe() {
    }

    public Recipe(String name, String instruction, String photoURL, List<RecipeIngredient> recipeIngredient, boolean isDraft, LocalDateTime draftCreationDateTime) {
        this.name = name;
        this.instruction = instruction;
        this.photoURL = photoURL;
        this.recipeIngredient = recipeIngredient;
        this.isDraft = isDraft;
        this.draftCreationDateTime = draftCreationDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public List<RecipeIngredient> getRecipeIngredient() {
        return recipeIngredient;
    }

    public void setRecipeIngredient(List<RecipeIngredient> recipeIngredient) {
        this.recipeIngredient = recipeIngredient;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public void setDraft(boolean draft) {
        isDraft = draft;
    }

    public LocalDateTime getDraftCreationDateTime() {
        return draftCreationDateTime;
    }

    public void setDraftCreationDateTime(LocalDateTime draftCreationDateTime) {
        this.draftCreationDateTime = draftCreationDateTime;
    }
}
