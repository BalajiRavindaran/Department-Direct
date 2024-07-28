package ca.concordia.departmentdirect.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "EventType", schema = "public")
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class EventType {
    private static final long serialVersionUID = -32392241272524698L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Name")
    private String name;

    }
