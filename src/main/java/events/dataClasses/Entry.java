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
	private long id;
	private final long parttakerId;
	private final long eventId;
}