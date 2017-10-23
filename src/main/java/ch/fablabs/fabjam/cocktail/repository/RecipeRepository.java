package ch.fablabs.fabjam.cocktail.repository;


import ch.fablabs.fabjam.cocktail.data.entities.Recipe;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends AbstractMongoRepository<Recipe> {
//	public RecipeRepository() {
//		super("recipe", Recipe.class);
//	}


}
