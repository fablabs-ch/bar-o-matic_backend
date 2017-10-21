package ch.fablabs.fabjam.cocktail.api;

import ch.fablabs.fabjam.cocktail.data.RecipeItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/api/command")
public class CommandWS {


	@RequestMapping(value="order", method = RequestMethod.POST)
	public RecipiesWS order(List<RecipeItem> items) {


	}
	@RequestMapping(value="stop", method = RequestMethod.POST)
	public String stop() {

	}

}
