package ch.fablabs.fabjam.cocktail.service.serial;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.concurrent.Executor;

@Service
public class SerialConnectionStarter implements CommandLineRunner {

	@Autowired
	private Executor threadPoolTaskExecutor;

	@Autowired
	private AutowireCapableBeanFactory autowireCapableBeanFactory;

	@Value("${serial.port:/dev/ttyUSB0}")
	private String port;

	private SerialConnection serialConnection;

	@Override
	public void run(String... strings) throws Exception {
		serialConnection = new SerialConnection(port);
		autowireCapableBeanFactory.autowireBean(serialConnection);
		threadPoolTaskExecutor.execute(serialConnection);
	}

	@PreDestroy
	protected void preDestroy() {
		serialConnection.close();
	}
}
