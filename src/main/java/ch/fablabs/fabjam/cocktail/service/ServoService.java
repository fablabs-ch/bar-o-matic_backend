package ch.fablabs.fabjam.cocktail.service;

import ch.fablabs.fabjam.cocktail.driver.PWMDevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ServoService {

	PWMDevice device;

	public ServoService() {
		try {
			device = new PWMDevice();
			device.setPWMFreqency(50);
		} catch (Exception ex) {
			LOG.error("Unable to init pwm device", ex);
		}
	}

	public String test(int servo, int angle) {
		LOG.info("address={}, bus={}", device.getAddress(), device.getBus());

		try {
			device.getChannel(0).setPWM(servo, mapAngle(angle));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ok";
	}

	/**
	 *
	 * @param angle [0, 180]
	 * @return
	 */
	private int mapAngle(int angle){
		int max = 520;
		int min = 120;
		return min+(max-min)/180*angle;
	}
}
