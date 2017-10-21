package ch.fablabs.fabjam.cocktail.data;

import lombok.Data;

import java.util.List;

@Data
public class Recipe implements ItfData {
	private Long id;
	private String name;
	private String description;
	private String imageUrl;
	private boolean shakeYourBody;
	private List<RecipeItem> items;
}
