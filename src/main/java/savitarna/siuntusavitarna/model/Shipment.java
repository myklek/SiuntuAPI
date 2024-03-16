package savitarna.siuntusavitarna.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column
    private String senderName;

    @Column
    private String senderCity;

    @Column
    private String recieverName;

    @Column
    private String recieverCity;


    @Column
    @Enumerated(EnumType.STRING)
    private ShipmentType shipmentType;

    //enum column packageSize with values: "S, M, L"
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
