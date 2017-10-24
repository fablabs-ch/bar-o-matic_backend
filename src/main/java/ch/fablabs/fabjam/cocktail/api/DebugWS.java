package ch.fablabs.fabjam.cocktail.api;

import ch.fablabs.fabjam.cocktail.service.ServoService;
import ch.fablabs.fabjam.cocktail.service.ServoServiceImpl;
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

	/**
	 * @param servo [0, 14]
	 * @param angle [0, 180]
	 * @return
	 */
	@RequestMapping(value = "servo/{servo}/{angle}")
	public String test(@PathVariable("servo") int servo, @PathVariable("angle") int angle) {
		return servoService.move(servo, angle);
	}

	@RequestMapping(value = "carier/{mm}")
	public long setCarrierPost(long mm) {
		return -1;
	}


	@RequestMapping(value = "tare", method = RequestMethod.POST)
	public String tare() {
		return "not implemented";
	}
}
