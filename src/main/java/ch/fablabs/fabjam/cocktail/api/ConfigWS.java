package ch.fablabs.fabjam.cocktail.api;

import ch.fablabs.fabjam.cocktail.data.Config;
import ch.fablabs.fabjam.cocktail.service.ServoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
public class ConfigWS {

	@Autowired
	private ConfigRepository configRepository;

	@RequestMapping("")
	public Config getConfig() {

	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public Config edit(Config config) {

	}

}
