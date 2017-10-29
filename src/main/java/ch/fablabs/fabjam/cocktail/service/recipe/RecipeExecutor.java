package ch.fablabs.fabjam.cocktail.service.recipe;

import ch.fablabs.fabjam.cocktail.data.entities.RecipeItem;
import ch.fablabs.fabjam.cocktail.data.recipe.RecipeItemFull;
import ch.fablabs.fabjam.cocktail.data.serial.SerialStatus;
import ch.fablabs.fabjam.cocktail.repository.IngredientRepository;
import ch.fablabs.fabjam.cocktail.service.recipe.actions.AbstractAction;
import ch.fablabs.fabjam.cocktail.service.recipe.actions.FillAction;
import ch.fablabs.fabjam.cocktail.service.recipe.actions.HomeAction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class RecipeExecutor implements Runnable {

	private final List<RecipeItemFull> items;
	private final Consumer<Boolean> endCallback;

	private SerialStatus status;

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		try {
			LOG.debug("Start recipe of {} items", items.size());

			//wait for first status
			checkStatusDelay();

			getActionList().stream()
				.forEach(action -> {
					do {
						action.run(status);
						checkStatusDelay();
					}while(action.isFinished());
				});

			Thread.sleep(50000);

			LOG.debug("End of recipe after {}ms", (System.currentTimeMillis() - start));
			endCallback.accept(true);
		} catch (InterruptedException e) {
			LOG.debug("Task interrupted after {}ms", (System.currentTimeMillis() - start));
		}
	}

	public void cancel() {
		LOG.warn("Task was cancelled");
		endCallback.accept(false);
	}

	protected List<AbstractAction> getActionList() {
		List<AbstractAction> actions = new LinkedList<>();
		actions.add(new HomeAction());
		actions.addAll(items.stream()
			.map(item -> new FillAction(item))
			.collect(Collectors.toList()));
		actions.add(new HomeAction());
		return actions;
	}

	protected void checkStatusDelay(){
		//TODO wait for status

		
	}
}
