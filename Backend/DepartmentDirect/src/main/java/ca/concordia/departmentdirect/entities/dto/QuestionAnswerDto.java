package ca.concordia.departmentdirect.entities.dto;

import ca.concordia.departmentdirect.entities.QuestionAnswer;
import ca.concordia.departmentdirect.entities.UserEvent;
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
public class QuestionAnswerDto {
    @Id
    private int id;
    private String studentId;
    private int departmentId;
    private int staffId;
    private String message;
    private Date datetime;
    private String category;
    private String status;
    private String role;

    public QuestionAnswer toModel() {
        QuestionAnswer questionAnswer = QuestionAnswer.builder().build();
        BeanUtils.copyProperties(this, questionAnswer);
        return questionAnswer;
    }

    public static QuestionAnswerDto fromModel(QuestionAnswer questionAnswer) {
        try {
            QuestionAnswerDto dto = QuestionAnswerDto.builder().build();
            BeanUtils.copyProperties(questionAnswer, dto);
            if (questionAnswer.getFutureStudent()!= null)
                dto.setStudentId(questionAnswer.getFutureStudent().getStudentId());
            if (questionAnswer.getStaff() != null)
                dto.setStaffId(questionAnswer.getStaff().getId());
            dto.setDepartmentId(questionAnswer.getDepartment().getId());
            dto.setRole(questionAnswer.getStaff() == null? "Student": "Staff");
            return dto;
        }catch (Exception er)
        {
            System.out.println(er.toString());
            return null;
        }
    }

    public static List<QuestionAnswerDto> fromModel(List<QuestionAnswer> questionAnswerList) {
        List<QuestionAnswerDto> dtoList = new ArrayList<>();
        for(QuestionAnswer entity: questionAnswerList)
            dtoList.add(fromModel(entity));
        return dtoList;
    }


}
