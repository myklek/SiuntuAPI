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

    public Shipment()
    {
        super();
    }
}