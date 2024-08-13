package ca.concordia.departmentdirect.entities.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class DistinctStudentDepartmentDto {
    private static final long serialVersionUID = -303922414972524698L;


    private String StudentID;
    private int DepartmentID;
    private String StudentName;
    private String DepartmentName;

/*
    @SqlResultSetMapping(
            name = "DistinctStudentDepartmentDtoMapping",
            classes = @ConstructorResult(
                    targetClass = DistinctStudentDepartmentDto.class,
                    columns = {
                            @ColumnResult(name = "StudentID", type = Integer.class),
                            @ColumnResult(name = "DepartmentID", type = Integer.class),
                            @ColumnResult(name = "StudentName", type = String.class),
                            @ColumnResult(name = "DepartmentName", type = String.class)
                    }
            )
    )

 */

}
