package savitarna.siuntusavitarna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import savitarna.siuntusavitarna.model.Shipment;
import savitarna.siuntusavitarna.service.ShipmentsService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ShipmentController
{
    @Autowired
    private ShipmentsService shipmentsService;

    //write getShipmentsByUserId method with url parameter id for user id
    @RequestMapping("/shipments")
    public @ResponseBody List<Shipment> getShipments()
    {
        return shipmentsService.findShipmentsByUserId();
    }

    //create a new shipment
    @PostMapping("/shipments/new")
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment)
    {
        System.out.println(shipment.toString());
        return ResponseEntity.ok(shipmentsService.createShipment(shipment));
    }



}
