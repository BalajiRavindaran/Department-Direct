package ca.concordia.departmentdirect.services;

import ca.concordia.departmentdirect.entities.Department;
import ca.concordia.departmentdirect.entities.QuestionAnswer;
import ca.concordia.departmentdirect.entities.Users;
import ca.concordia.departmentdirect.repository.QuestionAnswerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
