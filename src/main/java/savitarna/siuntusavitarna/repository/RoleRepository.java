package savitarna.siuntusavitarna.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import savitarna.siuntusavitarna.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>
{
    Role findByName(Role.RoleType name);
}