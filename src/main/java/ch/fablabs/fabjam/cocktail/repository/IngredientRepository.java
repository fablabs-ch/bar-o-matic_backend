package ch.fablabs.fabjam.cocktail.repository;


import ch.fablabs.fabjam.cocktail.data.entities.Ingredient;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientRepository extends AbstractRepository<Ingredient> {
	public IngredientRepository() {
		super("ingredient", Ingredient.class);
	}

}
