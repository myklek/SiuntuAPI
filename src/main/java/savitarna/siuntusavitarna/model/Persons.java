package savitarna.siuntusavitarna.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Persons {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column
	private String name;

	public Persons(int id, String name) {
		super();
		Id = id;
		this.name = name;
	}

	public Persons() {
		super();
	}
	
	
	

}
