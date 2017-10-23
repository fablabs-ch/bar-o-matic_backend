package ch.fablabs.fabjam.cocktail.service.serial;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;

@Slf4j
public class SerialConnection implements Runnable {

	@Autowired
	private AutowireCapableBeanFactory autowireCapableBeanFactory;

	private final String port;

	private Semaphore semaphore;

	private boolean running = true;

	public SerialConnection(String port) {
		this.port = port;
		semaphore = new Semaphore(1);
	}

	@Override
	public void run() {

		while (running) {
			try {
				semaphore.acquire();
				openPort();
			} catch (Exception ex) {
				LOG.error("Unable to open port {}", port, ex);
			}
			try {
				Thread.sleep(1000);
			} catch (Exception ex) {

			}
		}
	}

	public void close() {
		running = false;
	}

	protected void openPort() throws Exception {
		String[] portNames = SerialPortList.getPortNames();
		if (portNames.length == 0) {
			LOG.error("No serial port detected");
		} else {
			LOG.debug("Port detected:");
			Arrays.stream(portNames).forEach(p -> LOG.debug("\t{}", p));

			SerialPort serialPort = new SerialPort(port);
			try {
				serialPort.openPort();

				serialPort.setParams(SerialPort.BAUDRATE_115200,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

				serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
					SerialPort.FLOWCONTROL_RTSCTS_OUT);

				SerialReader sr = new SerialReader(serialPort);
				autowireCapableBeanFactory.autowireBean(sr);
				serialPort.addEventListener(sr, SerialPort.MASK_RXCHAR);

				//TODO release semaphore when connexion closed
//				serialPort.writeString("Hurrah!");
			} catch (SerialPortException ex) {
				System.out.println("There are an error on writing string to port т: " + ex);
			}
		}

	}
}
