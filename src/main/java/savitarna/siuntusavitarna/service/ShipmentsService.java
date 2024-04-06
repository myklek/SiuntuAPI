package savitarna.siuntusavitarna.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import savitarna.siuntusavitarna.model.*;
import savitarna.siuntusavitarna.repository.ServiceKioskRepository;
import savitarna.siuntusavitarna.repository.ShipmentRepository;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import savitarna.siuntusavitarna.model.Shipment;
import savitarna.siuntusavitarna.repository.UserRepository;

import java.util.Map;

@Service
public class ShipmentsService
{
    private final ShipmentRepository shipmentRepository;
    private final UserRepository userRepository;

    private final ServiceKioskRepository serviceKioskRepository;


    public ShipmentsService(ShipmentRepository shipmentRepository, UserRepository userRepository, ServiceKioskRepository serviceKioskRepository)
    {
        this.shipmentRepository = shipmentRepository;
        this.userRepository = userRepository;
        this.serviceKioskRepository = serviceKioskRepository;
    }


    public Shipment createShipment(Shipment shipment)
    {
        System.out.println("CREATE IN SERVICE");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();


        User user = userRepository.findByEmail(currentPrincipalName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Status status = new Status();
        status.setName(Status.StatusType.LABEL_CREATED);
        status.setShipment(shipment);

        shipment.setShipmentStatuses(List.of(status));
        shipment.setUser(user);

        return shipmentRepository.save(shipment);
    }


    public List<Shipment> findShipmentsByUserId()
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userRepository.findByEmail(currentPrincipalName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Shipment> shipments = shipmentRepository.findAllByUserId(user.getId());


        for (Shipment shipment : shipments)
        {
            if (!shipment.getUser().getId().equals(user.getId()))
            {
                throw new AccessDeniedException("Access denied");
            }
        }

        return shipments;
    }

    public Shipment findShipmentById(int id)
    {
        return shipmentRepository.findByIdAndIsCollectedFalse(id);
    }

    public Shipment updateShipment(int id, Map<String, String> shipment)
    {
        Shipment existingShipment = shipmentRepository.findById(id);

        System.out.println(shipment);
        if (existingShipment == null)
        {
            return null;
        }

        // Fetch the ServiceKiosk object using the provided ID
        ServiceKiosk serviceKiosk = serviceKioskRepository.findById(Integer.parseInt(shipment.get("serviceKioskId")));

        existingShipment.setPackageSize(Shipment.PackageSize.valueOf(shipment.get("packageSize")));

//add new status CANCELLED
//        if (shipment.get("status").equals("CANCELLED"))
//        {
        Status status = new Status();
        status.setName(Status.StatusType.CANCELLED);
        status.setShipment(existingShipment);
        existingShipment.getShipmentStatuses().add(status);
//        }

        System.out.println(existingShipment.getShipmentStatuses());

        // Set the fetched ServiceKiosk object to the existingShipment
        existingShipment.setServiceKiosk(serviceKiosk);

        return shipmentRepository.save(existingShipment);
    }
}