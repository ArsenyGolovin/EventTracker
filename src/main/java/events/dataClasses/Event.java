package events.dataClasses;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("EVENTS")
public class Event {

	@Id
	private int id;
	private String name;

	private int creatorId;

	private LocalDateTime startDatetime;
	private LocalDateTime endDatetime;
}