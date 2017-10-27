package ch.fablabs.fabjam.cocktail.service;

import ch.fablabs.fabjam.cocktail.data.serial.SerialStatus;
import ch.fablabs.fabjam.cocktail.data.type.JmsTopic;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.Subject;

@Service
public class ScaleService {

	private Subject<Integer, Integer> weightSubject;

	public ScaleService() {
		this.weightSubject = BehaviorSubject.create(-1);
	}

	public Observable<Integer> weightInGramme() {
		return weightSubject;
	}

	@JmsListener(destination = JmsTopic.SERIAL_STATUS)
	private void serialStatus(SerialStatus status) {
		this.weightSubject.onNext(status.getPayloadWeightGr());
	}
}
