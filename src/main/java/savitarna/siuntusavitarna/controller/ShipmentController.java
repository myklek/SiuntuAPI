package savitarna.siuntusavitarna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import savitarna.siuntusavitarna.model.Shipment;
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

    //write getShipmentsByUserId method with url parameter id for user id
    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/shipments")
    public @ResponseBody List<Shipment> getShipments()
    {
        return shipmentsService.findShipmentsByUserId();
    }

    //create a new shipment
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/shipments/new")
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment)
    {
        return ResponseEntity.ok(shipmentsService.createShipment(shipment));
    }

    //get shipment by id
    @PreAuthorize("hasRole('SERVICE_KIOSK')")
    @GetMapping("/shipments/{id}")
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
    @PatchMapping("/shipments/{id}")
    public ResponseEntity<Boolean> updateShipment(@PathVariable int id, @RequestBody Map<String, String> shipment)
    {
        System.out.println(shipment);
        Shipment updatedShipment = shipmentsService.updateShipment(id, shipment);
        if (updatedShipment == null)
        {
            throw new AccessDeniedException("Shipment not found");
        }
        return ResponseEntity.ok(true);
    }




}
