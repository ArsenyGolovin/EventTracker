package events.dataClasses;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("CLIENTS")
public class User {

	@Id
	private int id;
	private String name;
	private String password;
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof User && ((User)obj).getId() == this.getId();
	}
}