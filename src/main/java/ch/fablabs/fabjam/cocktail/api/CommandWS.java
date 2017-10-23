package ch.fablabs.fabjam.cocktail.api;

import ch.fablabs.fabjam.cocktail.data.entities.RecipeItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/command")
public class CommandWS {

	@RequestMapping(value = "order", method = RequestMethod.POST)
	public RecipiesWS order(List<RecipeItem> items) {
		return null;
	}

	@RequestMapping(value = "stop", method = RequestMethod.POST)
	public String stop() {
		return "not implemented";
	}

}
