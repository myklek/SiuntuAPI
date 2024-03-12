package savitarna.siuntusavitarna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import savitarna.siuntusavitarna.model.Persons;
import savitarna.siuntusavitarna.repository.PersonRepository;
@Service
public class PersonService {

	
	@Autowired
	private PersonRepository personRepository;
	
	
	public List<Persons> findAll(){
		return personRepository.findAll();
	}
	
	
}
