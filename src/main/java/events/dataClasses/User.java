package events.dataClasses;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "id" })
@Table("CLIENT")
public class User {

	@Id
	private long id;

	private String name;
	private String password;

	public boolean isTemp() {
		return id == 0;
	}
}