package ch.fablabs.fabjam.cocktail.data;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class History {
	private ZonedDateTime dateTime;
	private Recipe recipe;
}
