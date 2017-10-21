package ch.fablabs.fabjam.cocktail.api;

import ch.fablabs.fabjam.cocktail.service.ServoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestWS {

	@Autowired
	private ServoService servoService;

	@RequestMapping("{servo}/{angle}")
	public String test(@PathVariable("servo") int servo, @PathVariable("angle") int angle) {
		return servoService.test(servo, angle);
	}
}
