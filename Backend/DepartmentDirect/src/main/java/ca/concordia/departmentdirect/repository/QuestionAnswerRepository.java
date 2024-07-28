package ca.concordia.departmentdirect.repository;

import ca.concordia.departmentdirect.entities.Department;
import ca.concordia.departmentdirect.entities.QuestionAnswer;
import ca.concordia.departmentdirect.entities.SubscribeEvent;
import ca.concordia.departmentdirect.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionAnswerRepository  extends JpaRepository<QuestionAnswer, Integer> {
    List<QuestionAnswer> findByFutureStudentAndDepartment(Users futureStudent, Department department);
}
