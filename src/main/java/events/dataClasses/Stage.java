package events.dataClasses;

import org.springframework.data.annotation.Id;

import com.github.f4b6a3.ulid.UlidCreator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "id" })
public class Stage {

	@Id
	private String id = UlidCreator.getUlid().toString();

	private String title;
}
