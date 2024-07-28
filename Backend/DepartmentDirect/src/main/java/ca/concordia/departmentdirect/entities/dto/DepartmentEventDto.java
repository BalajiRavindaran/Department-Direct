package ca.concordia.departmentdirect.entities.dto;

import ca.concordia.departmentdirect.entities.DepartmentEvent;
import ca.concordia.departmentdirect.entities.EventType;
import ca.concordia.departmentdirect.entities.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentEventDto {
    @Id
    private int id;

    private int userDepartmentId;
    private int eventTypeId;

    private String content;

    private Date datetime;

    public DepartmentEvent toModel() {
        DepartmentEvent departmentEvent = DepartmentEvent.builder().build();
        BeanUtils.copyProperties(this, departmentEvent);
        return departmentEvent;
    }

    public static DepartmentEventDto fromModel(DepartmentEvent departmentEvent) {
        try {
            DepartmentEventDto dto = DepartmentEventDto.builder().build();
            BeanUtils.copyProperties(departmentEvent, dto);
            dto.setEventTypeId(departmentEvent.getEventType().getId());
            dto.setUserDepartmentId(departmentEvent.getUserDepartment().getId());
            return dto;
        }catch (Exception er)
        {
            System.out.println(er.toString());
            return null;
        }
    }

    public static List<DepartmentEventDto> fromModel(List<DepartmentEvent> departmentEventList) {
        List<DepartmentEventDto> dtoList = new ArrayList<>();
        for(DepartmentEvent entity: departmentEventList)
            dtoList.add(fromModel(entity));
        return dtoList;
    }


}
