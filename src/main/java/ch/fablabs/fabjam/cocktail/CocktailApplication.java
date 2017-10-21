package ch.fablabs.fabjam.cocktail;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.ast.Db;
import com.rethinkdb.gen.exc.ReqlOpFailedError;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class CocktailApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocktailApplication.class, args);
	}


	public static final RethinkDB r = RethinkDB.r;

	@Bean
	public Connection rethinkDbConnection() {
		return r.connection().hostname("rethinkdb").port(28015).connect();
	}

	@Bean
	public Db rethinkDbDatabase() {
		try {
			r.dbCreate("cocktail").run(rethinkDbConnection());
		}catch(ReqlOpFailedError ex){
			LOG.info("cocktail database already exists");
		}
		return r.db("cocktail");
	}
}
