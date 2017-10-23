package ch.fablabs.fabjam.cocktail.api;

import ch.fablabs.fabjam.cocktail.data.entities.ItfData;
import ch.fablabs.fabjam.cocktail.repository.AbstractRepository;
import lombok.Setter;

public class AbstractCrudWS<T extends ItfData> {


	@Setter
	public AbstractRepository<T> repository;

//	@RequestMapping("")
//	public List<T> findAll() {
//		return repository.findAll();
//	}
//
//	@RequestMapping(value = "{id}")
//	public T getOne(@RequestParam("id") long id) {
//		return repository.getOne(id);
//	}
//
//	@RequestMapping(value = "", method = RequestMethod.POST)
//	public T add(@RequestBody T entity) {
//		return repository.add(entity);
//	}
//
//	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
//	public T edit(@RequestParam("id") long id, @RequestBody T entity) {
//		return repository.edit(id, entity);
//	}
//
//	public T delete(long entityId) {
//		return repository.remove(entityId);
//	}
}
