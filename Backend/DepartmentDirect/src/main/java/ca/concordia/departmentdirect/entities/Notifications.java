package ca.concordia.departmentdirect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "Notifications", schema = "public")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Notifications {
    private static final long serialVersionUID = -32392241272509698L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Message")
    private String message;
    @Column(name = "Types")
    private String types;

    @ManyToOne
    @JoinColumn(name = "AdminID")
    private Users admin;

}
