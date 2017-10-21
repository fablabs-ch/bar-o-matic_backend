package ch.fablabs.fabjam.cocktail.data;

import lombok.Data;

import java.util.List;

@Data
public class Config {
	private List<IngredientConfig> ingredientConfig;
}
