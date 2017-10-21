package ch.fablabs.fabjam.cocktail.service;

import ch.fablabs.fabjam.cocktail.driver.PWMDevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@Profile("dev")
public class ServoServiceMock implements ServoService {


	public ServoServiceMock() {
	}

	public String move(int servo, int angle) {
		LOG.info("NOT IMPLEMENTED in dev mode");
		return "ok";
	}

}
