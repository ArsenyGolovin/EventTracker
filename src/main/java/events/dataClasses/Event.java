package events.dataClasses;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "id" })
public class Event implements Comparable<Event> {

	@Id
	private long id;

	private String name;
	private long creatorId;
	private LocalDateTime startDatetime;
	private LocalDateTime endDatetime;
	//@MappedCollection(idColumn = "ID", keyColumn = "EVENT_ID")
	private Set<Stage> stages = new LinkedHashSet<>();

	@Override
	public int compareTo(Event e) {
		return startDatetime.compareTo(e.startDatetime);
	}

	public void addStage() {
		Stage s = new Stage();
		s.setId(LocalDateTime.now().hashCode());
		//s.setId(UlidCreator.getUlid().toString());
		//s.setEventId(id);
		stages.add(s);
	}

	public int getStagesNum() {
		return stages.size();
	}
}