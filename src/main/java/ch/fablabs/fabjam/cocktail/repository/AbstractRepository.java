package ch.fablabs.fabjam.cocktail.repository;


import ch.fablabs.fabjam.cocktail.data.ItfData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.ast.Db;
import com.rethinkdb.gen.exc.ReqlNonExistenceError;
import com.rethinkdb.gen.exc.ReqlOpFailedError;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class AbstractRepository<T extends ItfData> {


	private static final ObjectMapper mapper = new ObjectMapper();

	private final String table;
	private final Class<T> clazz;

	public static final RethinkDB r = RethinkDB.r;

	@Autowired
	private Connection connection;

	@Autowired
	private Db db;

	@PostConstruct
	public void postConstruct() {
		try {
			db.tableCreate(table).run(connection);
			LOG.info("Table {} created", table);
		} catch (ReqlOpFailedError ex) {
			LOG.info("Table {} already exists", table);
		}
	}


//	private Map<Long, T> db = new HashMap<>();

	public List<T> findAll() {
		Cursor<Map> cursor = db.table(table).run(connection);
		List<T> list = new ArrayList<>();
		while(cursor.hasNext()){
			list.add(convert(cursor.next()));
		}
		return list;
	}

	public T getOne(long id) {
		Cursor<Map> cursor = db.table(table)
			.filter(r -> r.g("id").eq(id))
			.run(connection);
		if (cursor.hasNext()) {
			return convert(cursor.next());
		} else {
			return null;
		}
	}

	public T add(T entity) {
		long id = nextId();
		entity.setId(id);
		db.table(table).insert(entity).run(connection);
		return getOne(id);
	}

	public T edit(long id, T entity) {
		entity.setId(id);
		db.table(table)
			.filter(r -> r.g("id").eq(id))
			.update(entity)
			.run(connection);
		return getOne(id);
	}

	public T remove(long id) {
		T one = getOne(id);
		db.table(table)
			.filter(r -> r.g("id").eq(id))
			.delete();
		return one;
	}

	private T convert(Map map) {
		return mapper.convertValue(map, clazz);
	}

	private long nextId() {
		long id = 0;
		try {
			id = db.table(table)
				.map(r -> r.g("id"))
				.max()
				.run(connection);
		} catch (ReqlNonExistenceError ex) {
			//no id yet
		}
		return id + 1;
	}

}
