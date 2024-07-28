package ca.concordia.departmentdirect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "QuestionAnswer", schema = "public")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class QuestionAnswer {
    private static final long serialVersionUID = -32392241172524198L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "FutureApplicantID")
    private Users futureStudent;

    @ManyToOne
    @JoinColumn(name = "StaffID")
    private Users staff;

    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    private Department department;

    @Column(name = "Message")
    private String message;

    @Column(name = "Datetime")
    private Date datetime;

}
