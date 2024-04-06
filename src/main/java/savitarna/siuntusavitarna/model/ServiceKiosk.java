package savitarna.siuntusavitarna.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    public ServiceKiosk(int id)
    {
        this.Id = id;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column
    private String location;


    @Setter
    @Getter
    @JsonBackReference
    @OneToMany(mappedBy = "serviceKiosk")
    private Collection<Shipment> shipment;

    public ServiceKiosk()
    {

    }

}
