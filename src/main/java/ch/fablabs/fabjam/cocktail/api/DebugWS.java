package ch.fablabs.fabjam.cocktail.api;

import ch.fablabs.fabjam.cocktail.service.serial.SerialConnectionStarter;
import ch.fablabs.fabjam.cocktail.service.servo.ServoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/debug")
public class DebugWS {

	@Autowired
	private ServoService servoService;

	@Autowired
	private SerialConnectionStarter serialConnectionStarter;


	/**
	 * @param servo [0, 14]
	 * @param angle [0, 180]
	 * @return
	 */
	@RequestMapping(value = "servo/{servo}/{angle}")
	public String moveServo(@PathVariable("servo") int servo, @PathVariable("angle") int angle) {
		return servoService.move(servo, angle);
	}

	@RequestMapping(value = "carier/{mm}")
	public long setCarrierPost(@PathVariable("mm") long mm) {
		return -1;
	}


	@RequestMapping(value = "tare", method = RequestMethod.POST)
	public String tare() {
		return "not implemented";
	}

	@RequestMapping(value = "test/{message}")
	public String test(@PathVariable("message") String message) {
		serialConnectionStarter.sendMessage(message);
		return "ok";
	}
}
