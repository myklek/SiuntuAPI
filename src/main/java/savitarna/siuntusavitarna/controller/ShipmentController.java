package savitarna.siuntusavitarna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import savitarna.siuntusavitarna.model.Shipment;
import savitarna.siuntusavitarna.model.Package;
import savitarna.siuntusavitarna.service.PackageService;
import savitarna.siuntusavitarna.service.ShipmentsService;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ShipmentController
{

    @Autowired
    private ShipmentsService shipmentsService;

    @Autowired
    private PackageService packageService;


    //write getShipmentsByUserId method with url parameter id for user id
    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/shipment/all")
    public @ResponseBody List<Shipment> getShipments()
    {
        return shipmentsService.findShipmentsByUserId();
    }

    //create a new shipment
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/shipment/new")
    public ResponseEntity<Boolean> createShipment(@RequestBody Shipment shipment)
    {
        Shipment createdShipment = shipmentsService.createShipment(shipment);
        if(createdShipment == null)
        {
            throw new AccessDeniedException("Shipment not created");
        }
        return ResponseEntity.ok(true);
    }



    //create a new shipment
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/shipment/newcustompackage")
    public ResponseEntity<Integer> createShipmentWithCustomPackage(@RequestBody Shipment shipment)
    {
        Shipment createdShipment = shipmentsService.createShipmentWithCustomPackage(shipment);
        System.out.println(createdShipment.toString());
        if(createdShipment == null)
        {
            throw new AccessDeniedException("Shipment not created");
        }
        return ResponseEntity.ok(createdShipment.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/shipment/newstandardpackage")
    public ResponseEntity<Integer> createShipmentWithStandardPackage(@RequestBody Shipment shipment)
    {
        Shipment createdShipment = shipmentsService.createShipment(shipment);
        System.out.println(createdShipment.toString());
        if(createdShipment == null)
        {
            throw new AccessDeniedException("Shipment not created");
        }
        System.out.println(createdShipment.getId());
        return ResponseEntity.ok(createdShipment.getId());
    }

    //get shipment by id
    @PreAuthorize("hasRole('SERVICE_KIOSK')")
    @GetMapping("/shipment/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable int id)
    {
        Shipment shipment = shipmentsService.findShipmentById(id);
        if (shipment == null)
        {
            throw new AccessDeniedException("Shipment not found");
        }
        return ResponseEntity.ok(shipment);
    }

    //update shipment by id
    @PreAuthorize("hasRole('SERVICE_KIOSK')")
    @PatchMapping("/shipment/update")
    public ResponseEntity<Boolean> updateShipment(@RequestBody Shipment shipment)
    {
        System.out.println(shipment);
        Shipment updatedShipment = shipmentsService.updateShipment(shipment);
        if (updatedShipment == null)
        {
            throw new AccessDeniedException("Shipment not found");
        }
        return ResponseEntity.ok(true);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/shipment/packages")
    public @ResponseBody List<Package> getAllPackages()
    {
        return packageService.findAll();
    }







}
