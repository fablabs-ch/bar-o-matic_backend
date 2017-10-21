package ch.fablabs.fabjam.cocktail.data;

import lombok.Data;

@Data
public class IngredientConfig {
	private Long id;
	private Ingredient ingredient;
	private Long valveDistanceMm;
	private Long valveId;
}
