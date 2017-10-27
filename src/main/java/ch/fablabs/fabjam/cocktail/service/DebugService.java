package ch.fablabs.fabjam.cocktail.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DebugService implements CommandLineRunner {
	@Autowired
	private CarierService carierService;

	@Autowired
	private ScaleService scaleService;

	@Override
	public void run(String... strings) throws Exception {
		carierService.distanceFromHomeInMm().subscribe(v -> LOG.info("distance: {}mm", v));
		scaleService.weightInGramme().subscribe(v -> LOG.info("weight: {}gr", v));
	}
}
