package ca.concordia.departmentdirect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "DepartmentEvent", schema = "public")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class DepartmentEvent {
    private static final long serialVersionUID = -32392241172924198L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "UserDepartmentID")
    private UserDepartment userDepartment;

    @ManyToOne
    @JoinColumn(name = "EventTypeID")
    private EventType eventType;

    @Column(name = "Content")
    private String content;

    @Column(name = "Datetime")
    private Date datetime;



}
