package events.dataClasses;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "id" })
@Table("EVENTS")
public class Event implements Comparable<Event> {

	@Id
	private int id;
	private String name;

	private int creatorId;

	private LocalDateTime startDatetime;
	private LocalDateTime endDatetime;

	@Override
	public int compareTo(Event e) {
		return startDatetime.compareTo(e.startDatetime);
	}
}