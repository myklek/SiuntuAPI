package savitarna.siuntusavitarna.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonValue;
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

    public enum StatusType
    {

        LABEL_CREATED("Siunta sukurta"),
        COLLECTED("Siunta primta išsiutimui"),
        IN_DELIVERY("Siunta pristatoma"),
        DELIVERED("Siunta pristatyta"),
        CANCELLED("Siunta atšaukta");

        private final String prettyName;

        StatusType(String prettyName)
        {
            this.prettyName = prettyName;
        }

        @JsonValue
        public String getLabel()
        {
            return this.prettyName;
        }
    }

    public Status(StatusType name)
    {
        this.Name = name;
    }
}
