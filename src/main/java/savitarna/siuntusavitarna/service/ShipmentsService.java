package savitarna.siuntusavitarna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import savitarna.siuntusavitarna.model.Shipment;
import savitarna.siuntusavitarna.repository.ShipmentRepository;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import savitarna.siuntusavitarna.model.Shipment;
import savitarna.siuntusavitarna.model.User;
import savitarna.siuntusavitarna.repository.ShipmentRepository;
import savitarna.siuntusavitarna.repository.UserRepository;

import java.util.Optional;

//@Service
//public class ShipmentsService
//{
//
//    @Autowired
//    private ShipmentRepository shipmentRepository;
//    public List<Shipment> findShipmentsByUserId(int id){
//        return shipmentRepository.findAllByUserId(id);
//    }
//}

@Service
public class ShipmentsService
{
    private final ShipmentRepository shipmentRepository;
    private final UserRepository userRepository;

    public ShipmentsService(ShipmentRepository shipmentRepository, UserRepository userRepository)
    {
        this.shipmentRepository = shipmentRepository;
        this.userRepository = userRepository;
    }



    public List<Shipment> findShipmentsByUserId(Integer shipmentId)
    {
        List<Shipment> shipments = shipmentRepository.findAllByUserId(shipmentId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userRepository.findByEmail(currentPrincipalName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        for (Shipment shipment : shipments)
        {
            if (!shipment.getUser().getId().equals(user.getId()))
            {
                throw new AccessDeniedException("Access denied");
            }
        }

        return shipments;
    }
}