package events.dataClasses;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("ENTRIES")
public class Entry {

	@Id
	private int id;

	private int parttakerId;
	private int eventId;

	public Entry(int parttakerId, int eventId) {
		this.id = 0;
		this.parttakerId = parttakerId;
		this.eventId = eventId;
	}
}