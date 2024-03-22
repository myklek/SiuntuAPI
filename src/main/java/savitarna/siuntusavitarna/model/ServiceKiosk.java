package savitarna.siuntusavitarna.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
@Entity
@Table(name = "self_service_kiosks")
public class ServiceKiosk
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column
    private String location;


    @OneToMany(mappedBy = "serviceKiosk")
    private Collection<Shipment> shipment;

    public Collection<Shipment> getShipment()
    {
        return shipment;
    }

    public void setShipment(Collection<Shipment> shipment)
    {
        this.shipment = shipment;
    }
}
