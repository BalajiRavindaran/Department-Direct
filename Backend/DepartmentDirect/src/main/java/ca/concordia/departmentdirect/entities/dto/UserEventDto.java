package ca.concordia.departmentdirect.entities.dto;

import ca.concordia.departmentdirect.entities.DepartmentEvent;
import ca.concordia.departmentdirect.entities.UserEvent;
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
public class UserEventDto {
    @Id
    private int id;
    private int userId;
    private int eventId;
    private String eventType;
    private String content;
    private Date dateTime;


    public UserEvent toModel() {
        UserEvent userEvent = UserEvent.builder().build();
        BeanUtils.copyProperties(this, userEvent);
        return userEvent;
    }

    public static UserEventDto fromModel(UserEvent userEvent) {
        try {
            UserEventDto dto = UserEventDto.builder().build();
            BeanUtils.copyProperties(userEvent, dto);
            dto.setEventId(userEvent.getDepartmentEvent().getEventType().getId());
            dto.setUserId(userEvent.getUsers().getId());
            dto.setEventType(userEvent.getDepartmentEvent().getEventType().getName());
            dto.setContent(userEvent.getDepartmentEvent().getContent());
            dto.setDateTime(userEvent.getDepartmentEvent().getDatetime());
            return dto;
        }catch (Exception er)
        {
            System.out.println(er.toString());
            return null;
        }
    }

    public static List<UserEventDto> fromModel(List<UserEvent> userEventList) {
        List<UserEventDto> dtoList = new ArrayList<>();
        for(UserEvent entity: userEventList)
            dtoList.add(fromModel(entity));
        return dtoList;
    }



}
