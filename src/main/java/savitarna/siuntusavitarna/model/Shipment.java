package savitarna.siuntusavitarna.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "shipments")
public class Shipment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_kiosk_id")
    private ServiceKiosk serviceKiosk;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipment")
    private List<Status> shipmentStatuses;

    @Column
    private boolean isCollected;

    @Column
    private String senderName;

    @Column
    private String senderCity;

    @Column
    private String recieverName;

    @Column
    private String recieverCity;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column
    @Enumerated(EnumType.STRING)
    private ShipmentType shipmentType;

    @Column
    @Enumerated(EnumType.STRING)
    private PackageSize packageSize;

    public Shipment()
    {
        super();
    }

    public enum ShipmentType
    {
        SELF_SERVICE,
        SELF_PACK
    }
    public enum PackageSize
    {
        S,
        M,
        L
    }

}
