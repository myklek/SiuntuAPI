package savitarna.siuntusavitarna.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import savitarna.siuntusavitarna.model.ServiceKiosk;

@Repository
public interface ServiceKioskRepository  extends CrudRepository<ServiceKiosk, Integer>
{
    ServiceKiosk findById(int id);
}
