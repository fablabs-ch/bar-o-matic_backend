package ch.fablabs.fabjam.cocktail.api;

import ch.fablabs.fabjam.cocktail.data.Config;
import ch.fablabs.fabjam.cocktail.service.ServoService;
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
	private ConfigRepository configRepository;

	/**
	 * @param servo [0, 14]
	 * @param angle [0, 180]
	 * @return
	 */
	@RequestMapping(value = "servo/{servo}/{angle}")
	public String test(@PathVariable("servo") int servo, @PathVariable("angle") int angle) {
		return servoService.test(servo, angle);
	}

	@RequestMapping(value = "carier/{mm}")
	public long setCarrierPost(long mm) {

	}


	@RequestMapping(value = "tare", method = RequestMethod.POST)
	public String tare() {

	}
}
