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

    private boolean draft;
    private LocalDateTime draftCreationDateTime;

    private int likeCounter;

    /**
     * todo
     * kategorie - zastanów sie czy ma to być select, czy po prostu pole string?
     * licznik do przechowywania ilości lików w przepisie - pamiętaj że kliknięcie lubię to  dodaje, a odkliknęcie odejmuje
     */


    public Recipe() {
    }

    public Recipe(String name, String instruction, String photoURL, List<RecipeIngredient> recipeIngredient, boolean draft, LocalDateTime draftCreationDateTime, Integer likeCounter) {
        this.name = name;
        this.instruction = instruction;
        this.photoURL = photoURL;
        this.recipeIngredient = recipeIngredient;
        this.draft = draft;
        this.draftCreationDateTime = draftCreationDateTime;
        this.likeCounter = likeCounter;
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
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public LocalDateTime getDraftCreationDateTime() {
        return draftCreationDateTime;
    }

    public void setDraftCreationDateTime(LocalDateTime draftCreationDateTime) {
        this.draftCreationDateTime = draftCreationDateTime;
    }


    public int getLikeCounter() {
        return likeCounter;
    }

    public void setLikeCounter(int likeCounter) {
        this.likeCounter = likeCounter;
    }
}
