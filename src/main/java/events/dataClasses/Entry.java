package events.dataClasses;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = { "parttakerId", "eventId" })
public class Entry {

	@Id
	private int id;

	private final int parttakerId;
	private final int eventId;
}