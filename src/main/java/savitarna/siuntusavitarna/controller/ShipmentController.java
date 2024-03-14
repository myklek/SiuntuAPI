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
    @RequestMapping("/shipments/{user_id}")
    public @ResponseBody List<Shipment> getShipments(@PathVariable(value="user_id") String id)
    {
        System.out.println("get Shipments");
        List<Shipment> shipments = shipmentsService.findShipmentsByUserId(Integer.parseInt(id));
        for (Shipment shipment : shipments)
        {
            System.out.println(shipment.getRecieverCity()) ;
        }
        return shipmentsService.findShipmentsByUserId(Integer.parseInt(id));
    }
}
