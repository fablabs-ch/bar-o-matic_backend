package ch.fablabs.fabjam.cocktail.repository;


import ch.fablabs.fabjam.cocktail.data.IngredientConfig;
import org.springframework.stereotype.Repository;

@Repository
public class IngredientConfigRepository extends AbstractRepository<IngredientConfig> {
	public IngredientConfigRepository () {
		super("ingredient_config", IngredientConfig.class);
	}

}
