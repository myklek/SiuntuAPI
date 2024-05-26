package savitarna.siuntusavitarna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipment")
    private List<Status> shipmentStatuses;

    @Column
    private boolean isCollected;

    @Column
    private String senderName;

    @Column
    private String senderAddress;

    @Column
    String senderPhoneNumber;

    @Column
    private String recieverName;

    @Column
    private String recieverAddress;

    @Column
    String recieverPhoneNumber;


    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column
    @Enumerated(EnumType.STRING)
    private ShipmentType shipmentType;

    @ManyToOne
    @JoinColumn(name = "package_id")
    @JsonProperty("package")
    private Package aPackage;

    public Shipment()
    {
        super();
    }

    public enum ShipmentType
    {
        SELF_SERVICE,
        SELF_PACK
    }


}
