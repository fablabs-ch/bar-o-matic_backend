package ch.fablabs.fabjam.cocktail.service.recipe.actions;

import ch.fablabs.fabjam.cocktail.data.serial.SerialStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

abstract public class AbstractAction {

	@Setter(AccessLevel.PROTECTED)
	@Getter
	private boolean finished = true;

	abstract public void run(SerialStatus status);

}
