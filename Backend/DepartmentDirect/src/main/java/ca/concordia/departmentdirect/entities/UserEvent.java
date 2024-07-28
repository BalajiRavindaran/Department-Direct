package ca.concordia.departmentdirect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "UserEvent", schema = "public")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class UserEvent {
    private static final long serialVersionUID = -32392241672924198L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "UserID")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "DepartmentEventID")
    private DepartmentEvent departmentEvent;

}
