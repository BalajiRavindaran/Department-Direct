package ca.concordia.departmentdirect.services;

import ca.concordia.departmentdirect.entities.QuestionAnswer;
import ca.concordia.departmentdirect.entities.dto.CategoryPercentageByDepDto;
import ca.concordia.departmentdirect.entities.dto.CategoryPercentageDto;
import ca.concordia.departmentdirect.entities.dto.DistinctStudentDepartmentDto;

import java.util.List;

public interface QuestionAnswerService {
    List<QuestionAnswer> getQuestionAnswersByFutureStudentIdandDepartmentID(int futureStudentId, int departmentId);
    QuestionAnswer createQuestionAnswer(QuestionAnswer questionAnswer);
    void UpdateStudentStatus(Integer studentId, Integer departmentId);
    List<DistinctStudentDepartmentDto> findByDistinctStudentIDAndDepartmentID();
    List<DistinctStudentDepartmentDto> findByDistinctByDepartmentID(Integer departmentId);
    List<CategoryPercentageDto> getCategoryPercentage();

    List<CategoryPercentageByDepDto> getCategoryPercentageByDepartment();
}
