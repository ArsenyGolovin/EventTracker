package events.dataClasses;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "id" })
public class Stage {

	@Id
	private long id;

	private String title;
}
