package ca.concordia.departmentdirect.services;

import ca.concordia.departmentdirect.entities.Department;
import ca.concordia.departmentdirect.entities.QuestionAnswer;
import ca.concordia.departmentdirect.entities.Users;
import ca.concordia.departmentdirect.entities.dto.CategoryPercentageByDepDto;
import ca.concordia.departmentdirect.entities.dto.CategoryPercentageDto;
import ca.concordia.departmentdirect.entities.dto.DistinctStudentDepartmentDto;
import ca.concordia.departmentdirect.repository.QuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionAnswerServiceImpl implements QuestionAnswerService{
    private final QuestionAnswerRepository questionAnswerRepository;

    @Override
    public List<QuestionAnswer> getQuestionAnswersByFutureStudentIdandDepartmentID(int futureStudentId, int departmentId) {
        return questionAnswerRepository.findByFutureStudentAndDepartment(Users.builder().id(futureStudentId).build(),
                Department.builder().id(departmentId).build());
    }

    @Override
    public QuestionAnswer createQuestionAnswer(QuestionAnswer questionAnswer) {
        return questionAnswerRepository.save(questionAnswer);
    }

    @Override
    public void UpdateStudentStatus(Integer studentId, Integer departmentId) {
        questionAnswerRepository.updateQuestionAnswer(studentId, departmentId);
    }

    @Override
    public List<DistinctStudentDepartmentDto> findByDistinctStudentIDAndDepartmentID() {
        List<Object[]> listObject =  questionAnswerRepository.findByDistinctStudentIDAndDepartmentID();
        List<DistinctStudentDepartmentDto> distinctStudentDepartmentDtos = new ArrayList<>();

        for(Object[] object: listObject){
            distinctStudentDepartmentDtos.add(DistinctStudentDepartmentDto.builder()
                            .StudentID((String) object[0])
                    .DepartmentID((Integer)object[1])
                            .DepartmentName((String)object[3])
                            .StudentName((String) object[2])
                            .build());
        }return distinctStudentDepartmentDtos;
    }

    @Override
    public List<DistinctStudentDepartmentDto> findByDistinctByDepartmentID(Integer departmentId) {
        List<Object[]> listObject =  questionAnswerRepository.findByDistinctByDepartmentID(departmentId);
        List<DistinctStudentDepartmentDto> distinctStudentDepartmentDtos = new ArrayList<>();

        for(Object[] object: listObject){
            distinctStudentDepartmentDtos.add(DistinctStudentDepartmentDto.builder()
                    .StudentID((String) object[0])
                    .DepartmentID((Integer)object[1])
                    .DepartmentName((String)object[3])
                    .StudentName((String) object[2])
                    .build());
        }return distinctStudentDepartmentDtos;
    }

    @Override
    public List<CategoryPercentageDto> getCategoryPercentage() {
        List<Object[]> listObject =  questionAnswerRepository.getCategoryPercentage();
        List<CategoryPercentageDto> categoryPercentageDtoList = new ArrayList<>();

        for(Object[] object: listObject){
            categoryPercentageDtoList.add(CategoryPercentageDto.builder()
                    .count((Long) object[0])
                    .category((String) object[1])
                    .percentage((BigDecimal) object[2])
                    .build());
        }
        return categoryPercentageDtoList;

    }

    @Override
    public List<CategoryPercentageByDepDto> getCategoryPercentageByDepartment() {
        List<Object[]> listObject =  questionAnswerRepository.getCategoryPercentageByDepartment();
        List<CategoryPercentageByDepDto> categoryPercentageByDepDtoList = new ArrayList<>();

        for(Object[] object: listObject){
            categoryPercentageByDepDtoList.add(CategoryPercentageByDepDto.builder()
                    .count((Long) object[0])
                    .category((String) object[1])
                    .departmentName((String) object[2])
                    .percentage((BigDecimal) object[3])
                    .build());
        }
        return categoryPercentageByDepDtoList;

    }
}
