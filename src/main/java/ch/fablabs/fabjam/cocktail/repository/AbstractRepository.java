package ch.fablabs.fabjam.cocktail.repository;


import ch.fablabs.fabjam.cocktail.data.ItfData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractRepository<T extends ItfData> {

	private Map<Long, T> db = new HashMap<>();

	public List<T> findAll() {
		return new ArrayList<>(db.values());
	}

	public T add(T entity) {
		long id = nextId();
		entity.setId(id);
		db.put(id, entity);
	}
	public T edit(long id, T entity) {
		entity.setId(id);
		db.put(id, entity);
	}

	private long nextId() {
		return db.keySet().stream().mapToLong(v -> v).max().orElse(0) + 1;
	}

}
