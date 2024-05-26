package savitarna.siuntusavitarna.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import savitarna.siuntusavitarna.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
    Optional<User> findByEmail(String email);
}
