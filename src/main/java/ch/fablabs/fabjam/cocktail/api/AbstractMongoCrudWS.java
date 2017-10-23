package ch.fablabs.fabjam.cocktail.api;

import ch.fablabs.fabjam.cocktail.data.ItfData;
import ch.fablabs.fabjam.cocktail.repository.AbstractMongoRepository;
import ch.fablabs.fabjam.cocktail.repository.AbstractRepository;
import lombok.Setter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class AbstractMongoCrudWS<T extends ItfData> {

	@Setter
	public AbstractMongoRepository<T> repository;

	@RequestMapping("")
	public List<T> findAll() {
		return repository.findAll();
	}

	@RequestMapping(value = "{id}")
	public T getOne(@RequestParam("id") long id) {
		return repository.findOne(id);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public T add(@RequestBody T entity) {
		return repository.insert(entity);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public T edit(@RequestParam("id") long id, @RequestBody T entity) {
		return repository.save(entity);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public T delete(long entityId) {
		T ret = repository.findOne(entityId);
		repository.delete(entityId);
		return ret;
	}
}
