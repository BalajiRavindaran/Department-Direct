package ca.concordia.departmentdirect.entities.dto;

import ca.concordia.departmentdirect.entities.UserEvent;
import ca.concordia.departmentdirect.entities.Users;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDto {
    @Id
    private int id;
    private String fullname;
    private String email;
    private String studentId;
    private String notifications;
    private String dateOfBirth;
    private String password;
    private String role;


    public static UsersDto fromModel(Users users) {
        try {
            UsersDto dto = UsersDto.builder().build();
            BeanUtils.copyProperties(users, dto);
            dto.password = "";
            return dto;
        }catch (Exception er)
        {
            System.out.println(er.toString());
            return null;
        }
    }

}
