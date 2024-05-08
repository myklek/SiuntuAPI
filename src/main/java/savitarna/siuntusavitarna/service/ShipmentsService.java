package savitarna.siuntusavitarna.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import savitarna.siuntusavitarna.model.*;
import savitarna.siuntusavitarna.model.Package;
import savitarna.siuntusavitarna.repository.PackageRepository;
import savitarna.siuntusavitarna.repository.ServiceKioskRepository;
import savitarna.siuntusavitarna.repository.ShipmentRepository;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import savitarna.siuntusavitarna.model.Shipment;
import savitarna.siuntusavitarna.repository.UserRepository;
import savitarna.siuntusavitarna.projection.ShipmentProjection;

import java.util.Map;

@Service
public class ShipmentsService
{
    private final ShipmentRepository shipmentRepository;

    private final PackageRepository packageRepository;
    private final UserRepository userRepository;

    private final ServiceKioskRepository serviceKioskRepository;


    public ShipmentsService(ShipmentRepository shipmentRepository, UserRepository userRepository, ServiceKioskRepository serviceKioskRepository, PackageRepository packageRepository)
    {
        this.shipmentRepository = shipmentRepository;
        this.userRepository = userRepository;
        this.serviceKioskRepository = serviceKioskRepository;
        this.packageRepository = packageRepository;
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

//        if (shipment.getShipmentType() == Shipment.ShipmentType.SELF_PACK)
//        {
//            shipment.setCollected(true);
//        }

        shipment.setShipmentStatuses(List.of(status));
        shipment.setUser(user);

        return shipmentRepository.save(shipment);
    }

    public Shipment createShipmentWithCustomPackage(Shipment shipment)
    {
        System.out.println("CREATE IN SERVICE CUSTOM PACKAGE");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userRepository.findByEmail(currentPrincipalName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Status status = new Status();
        status.setName(Status.StatusType.LABEL_CREATED);
        status.setShipment(shipment);

        Package aPackage = new Package();
        aPackage.setHeight(shipment.getAPackage().getHeight());
        aPackage.setLength(shipment.getAPackage().getLength());
        aPackage.setWidth(shipment.getAPackage().getWidth());
        aPackage.setCustom(true);

        packageRepository.save(aPackage);

        shipment.setCollected(true);
        shipment.setShipmentType(Shipment.ShipmentType.SELF_SERVICE);
        shipment.setShipmentStatuses(List.of(status));
        shipment.setUser(user);
        shipment.setAPackage(aPackage);

        return shipmentRepository.save(shipment);
    }

    public Shipment createShipmentWithStandardPackage(Shipment shipment)
    {
        System.out.println("CREATE IN SERVICE");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();


        User user = userRepository.findByEmail(currentPrincipalName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Status status = new Status();
        status.setName(Status.StatusType.LABEL_CREATED);
        status.setShipment(shipment);

        if (shipment.getShipmentType() == Shipment.ShipmentType.SELF_PACK)
        {
            shipment.setCollected(true);
        }

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

    public Shipment updateShipment(Shipment shipment)
    {
        Shipment existingShipment = shipmentRepository.findById(shipment.getId());

//        System.out.println(existingShipment.toString());
//        System.out.println(existingShipment.getAPackage().toString());
        if (existingShipment == null)
        {
            return null;
        }

        Package aPackage;

        if(shipment.getAPackage().getId() == 0)
        {
            System.out.println("create new package");
            aPackage = new Package();


            aPackage.setHeight(shipment.getAPackage().getHeight());
            aPackage.setLength(shipment.getAPackage().getLength());
            aPackage.setWidth(shipment.getAPackage().getWidth());
            aPackage.setCustom(true);
            packageRepository.save(aPackage);
        }
        else {
            System.out.println("use existing package");
            aPackage = packageRepository.findById(shipment.getAPackage().getId());
        }






        Status status = new Status();
        status.setName(Status.StatusType.COLLECTED);
        status.setShipment(existingShipment);
        existingShipment.setAPackage(aPackage);
        existingShipment.getShipmentStatuses().add(status);
        existingShipment.setCollected(true);
        return shipmentRepository.save(existingShipment);
    }
}