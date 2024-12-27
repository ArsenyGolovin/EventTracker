package events.dataClasses;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("CLIENTS")
@NoArgsConstructor
public class User {

	@Id
	private int id;
	private String name;
	private String password;

}
