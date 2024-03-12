package savitarna.siuntusavitarna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import savitarna.siuntusavitarna.model.Persons;

@Repository
public interface PersonRepository extends JpaRepository<Persons, Integer> {

}
