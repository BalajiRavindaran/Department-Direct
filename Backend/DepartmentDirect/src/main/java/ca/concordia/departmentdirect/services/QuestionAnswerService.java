package ca.concordia.departmentdirect.services;

import ca.concordia.departmentdirect.entities.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerService {
    List<QuestionAnswer> getQuestionAnswersByFutureStudentIdandDepartmentID(int futureStudentId, int departmentId);
    QuestionAnswer createQuestionAnswer(QuestionAnswer questionAnswer);
}
