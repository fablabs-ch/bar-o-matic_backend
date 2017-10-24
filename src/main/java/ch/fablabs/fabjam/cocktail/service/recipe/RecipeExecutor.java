package ch.fablabs.fabjam.cocktail.service.recipe;

import ch.fablabs.fabjam.cocktail.data.entities.RecipeItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
public class RecipeExecutor implements Runnable {

	private final List<RecipeItem> items;
	private final Consumer<Boolean> endCallback;

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		try {
			LOG.debug("Start recipe of {} items", items.size());

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
}
