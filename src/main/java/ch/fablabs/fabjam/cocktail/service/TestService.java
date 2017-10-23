package ch.fablabs.fabjam.cocktail.service;

import ch.fablabs.fabjam.cocktail.data.serial.SerialStatus;
import ch.fablabs.fabjam.cocktail.data.type.JmsTopic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

	@JmsListener(destination = JmsTopic.SERIAL_STATUS)
	public void receivedStatus(SerialStatus status) {
		LOG.info("Status received : {}", status);
	}
}
