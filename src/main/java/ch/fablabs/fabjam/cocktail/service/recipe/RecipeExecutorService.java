package ch.fablabs.fabjam.cocktail.service.recipe;

import ch.fablabs.fabjam.cocktail.data.entities.Recipe;
import ch.fablabs.fabjam.cocktail.data.entities.RecipeItem;
import ch.fablabs.fabjam.cocktail.exception.BusyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

@Slf4j
@Service
public class RecipeExecutorService {

	private AtomicBoolean working;

	@Autowired
	private AsyncTaskExecutor executor;

	@Autowired
	private AutowireCapableBeanFactory autowireCapableBeanFactory;

	private Optional<Map.Entry<RecipeExecutor, Future<?>>> currentRecipeExecutor;

	public RecipeExecutorService() {
		this.working = new AtomicBoolean(false);
	}

	public void execute(List<RecipeItem> items) {
		boolean wasWorking = working.getAndSet(true);
		if (wasWorking) {
			LOG.error("A task is already ongoing, we cannot execute another order");
			throw new BusyException("A recipe is already running, wait for the end");
		}

		Consumer<Boolean> resultConsumer = result -> {
			LOG.info("Execution ends with a result: {}", result);
			this.working.set(false);
			currentRecipeExecutor = Optional.empty();
		};
		RecipeExecutor recipeExecutor = new RecipeExecutor(items, resultConsumer);

		Future<?> future = executor.submit(recipeExecutor);

		currentRecipeExecutor = Optional.of(new AbstractMap.SimpleEntry<RecipeExecutor, Future<?>>(recipeExecutor, future));
	}

	public void cancel() {
		currentRecipeExecutor.ifPresent(e -> {
			e.getKey().cancel();
			e.getValue().cancel(true);
			working.set(false);
			currentRecipeExecutor = Optional.empty();
		});
	}
}
