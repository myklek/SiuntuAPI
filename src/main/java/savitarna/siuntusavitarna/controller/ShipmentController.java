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

    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/shipment/all")
    public @ResponseBody List<Shipment> getShipments()
    {
        return shipmentsService.findShipmentsByUserId();
    }

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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/shipment/newcustompackage")
    public ResponseEntity<Integer> createShipmentWithCustomPackage(@RequestBody Shipment shipment)
    {
        Shipment createdShipment = shipmentsService.createShipmentWithCustomPackage(shipment);
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
        if(createdShipment == null)
        {
            throw new AccessDeniedException("Shipment not created");
        }
        return ResponseEntity.ok(createdShipment.getId());
    }

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

    @PreAuthorize("hasRole('SERVICE_KIOSK')")
    @PatchMapping("/shipment/update")
    public ResponseEntity<Boolean> updateShipment(@RequestBody Shipment shipment)
    {
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
