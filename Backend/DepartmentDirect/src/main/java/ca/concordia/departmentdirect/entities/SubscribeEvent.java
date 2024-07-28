package ca.concordia.departmentdirect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "SubscribeEvent", schema = "public")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class SubscribeEvent {
    private static final long serialVersionUID = -32392241172524198L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "UserID")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    private Department department;

}
