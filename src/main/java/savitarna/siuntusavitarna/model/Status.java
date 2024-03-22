package savitarna.siuntusavitarna.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "status")
public class Status
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusType Name;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "shipments_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Shipment shipment;

    public Status()
    {

    }


    //Status enum
    public enum StatusType
    {
        LABEL_CREATED,
        COLLECTED,
        IN_DELIVERY,
        DELIVERED,
        CANCELLED
    }

    public Status(StatusType name)
    {
        this.Name = name;
    }
}
