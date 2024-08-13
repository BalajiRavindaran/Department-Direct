package ca.concordia.departmentdirect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table(name = "Users", schema = "public")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Users {
    private static final long serialVersionUID = -32392241272504198L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Fullname")
    private String fullname;
    @Column(name = "Password")
    private String password;
    @Column(name = "Email")
    private String email;
    @Column(name = "Role")
    private String role;
    @Column(name = "StudentID")
    private String studentId;
    @Column(name = "Notifications")
    private String notifications;
    @Column(name = "DateOfBirth")
    private String dateOfBirth;




}
