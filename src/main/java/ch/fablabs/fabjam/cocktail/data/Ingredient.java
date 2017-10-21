package ch.fablabs.fabjam.cocktail.data;

import lombok.Data;

@Data
public class Ingredient implements ItfData {
	private Long id;
	private String name;
	private IngredientType type;
	private boolean manual;
}
