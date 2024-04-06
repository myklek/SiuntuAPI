package savitarna.siuntusavitarna.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savitarna.siuntusavitarna.model.Shipment;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer>
{
    List<Shipment> findAllByUserId(int userId);

    Shipment findById(int id);
    Shipment findByIdAndIsCollectedFalse(int id);


}
