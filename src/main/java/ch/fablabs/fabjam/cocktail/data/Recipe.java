package ch.fablabs.fabjam.cocktail.data;

import lombok.Data;

import java.util.List;

@Data
public class Recipe {
	private String name;
	private String description;
	private String imageUrl;
	private List<RecipeItem> items;
}
