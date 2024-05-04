package savitarna.siuntusavitarna.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import savitarna.siuntusavitarna.model.Shipment;
import savitarna.siuntusavitarna.projection.ShipmentProjection;

import java.util.List;

@Repository
public interface ShipmentRepository extends CrudRepository<Shipment, Integer>
{
    List<Shipment> findAllByUserId(int userId);

    Shipment findById(int id);
    Shipment findByIdAndIsCollectedFalse(int id);


}
