package savitarna.siuntusavitarna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "package")
public class Package
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Double width;

    @Column
    private Double height;

    @Column
    private Double length;

    @Column
    private Double weight;

    @Column
    private boolean custom;

    @JsonIgnore
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @Column
    private String name;


}
