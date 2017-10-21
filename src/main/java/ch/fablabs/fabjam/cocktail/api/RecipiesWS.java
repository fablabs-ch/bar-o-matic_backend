package ch.fablabs.fabjam.cocktail.api;

import ch.fablabs.fabjam.cocktail.data.Recipe;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/recipies")
public class RecipiesWS {

	public List<Recipe> findAll() {

	}

	public Recipe add(Recipe recipe) {

	}

	public Recipe edit(long recipeId, Recipe recipe) {

	}

	public Recipe delete(long recipeId) {

	}
}
