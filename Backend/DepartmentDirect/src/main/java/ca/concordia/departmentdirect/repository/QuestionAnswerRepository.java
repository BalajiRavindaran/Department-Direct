package ca.concordia.departmentdirect.repository;

import ca.concordia.departmentdirect.entities.Department;
import ca.concordia.departmentdirect.entities.QuestionAnswer;
import ca.concordia.departmentdirect.entities.SubscribeEvent;
import ca.concordia.departmentdirect.entities.Users;
import ca.concordia.departmentdirect.entities.dto.DistinctStudentDepartmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface QuestionAnswerRepository  extends JpaRepository<QuestionAnswer, Integer> {
    List<QuestionAnswer> findByFutureStudentAndDepartment(Users futureStudent, Department department);
    @Modifying
    @Query(value = "UPDATE public.\"QuestionAnswer\" " +
            "SET \"Status\" = 'Inactive' " +
            "WHERE \"FutureApplicantID\" = :studentid AND \"DepartmentID\" =:depid", nativeQuery = true)
    void updateQuestionAnswer(@Param("studentid") Integer studentid, @Param("depid") Integer depid);


    @Query(value = "SELECT DISTINCT \"Users\".\"StudentID\" \"StudentID\", \"QuestionAnswer\".\"DepartmentID\",\n" +
            " \"Users\".\"Fullname\" \"StudentName\",\n" +
            " \"Department\".\"Name\" \"DepartmentName\"\n" +
            "FROM public.\"QuestionAnswer\"\n" +
            "LEFT JOIN public.\"Users\" ON \"QuestionAnswer\".\"FutureApplicantID\" = \"Users\".\"ID\"\n" +
            "LEFT JOIN public.\"Department\" ON \"Department\".\"ID\" = \"QuestionAnswer\".\"DepartmentID\"\n" +
            "WHERE \"QuestionAnswer\".\"Status\" ='Active'", nativeQuery = true)
    List<java.lang.Object[]> findByDistinctStudentIDAndDepartmentID();

    @Query(value = "SELECT DISTINCT \"Users\".\"StudentID\" \"StudentID\", \"QuestionAnswer\".\"DepartmentID\",\n" +
            " \"Users\".\"Fullname\" \"StudentName\",\n" +
            " \"Department\".\"Name\" \"DepartmentName\"\n" +
            "FROM public.\"QuestionAnswer\"\n" +
            "LEFT JOIN public.\"Users\" ON \"QuestionAnswer\".\"FutureApplicantID\" = \"Users\".\"ID\"\n" +
            "LEFT JOIN public.\"Department\" ON \"Department\".\"ID\" = \"QuestionAnswer\".\"DepartmentID\"\n" +
            "WHERE \"QuestionAnswer\".\"Status\" ='Active' AND \"QuestionAnswer\".\"DepartmentID\" =:depid", nativeQuery = true)
    List<java.lang.Object[]> findByDistinctByDepartmentID(@Param("depid") Integer depid);

    @Query(value = "SELECT COUNT(\"ID\") \"Count\", \"Category\",\n" +
            "(COUNT(\"ID\") * 100.0 / (SELECT COUNT (\"ID\") FROM public.\"QuestionAnswer\")) \"Percentage\"\n" +
            "FROM public.\"QuestionAnswer\"\n" +
            "GROUP BY \"Category\"", nativeQuery = true)
    List<java.lang.Object[]> getCategoryPercentage();

    @Query(value = "SELECT COUNT(\"QuestionAnswer\".\"ID\") \"Count\", \"Category\", \"Department\".\"Name\" \"DepartmentName\",\n" +
            "(COUNT(\"QuestionAnswer\".\"ID\") * 100.0 / (SELECT COUNT (\"ID\") FROM public.\"QuestionAnswer\")) \"Percentage\"\n" +
            "FROM public.\"QuestionAnswer\"\n" +
            "LEFT JOIN public.\"Department\" ON \"QuestionAnswer\".\"DepartmentID\" = \"Department\".\"ID\"\n" +
            "GROUP BY \"Category\", \"Department\".\"Name\"", nativeQuery = true)
    List<java.lang.Object[]> getCategoryPercentageByDepartment();


    //   @Query("SELECT new ca.concordia.departmentdirect.entities.dto.DistinctStudentDepartmentDto(qa.futureApplicantID, qa.departmentID, u.fullname, d.name) FROM QuestionAnswer qa LEFT JOIN Users u ON qa.futureApplicantID = u.id LEFT JOIN Department d ON d.id = qa.departmentID WHERE qa.status = 'Active'")
 //   List<DistinctStudentDepartmentDto> findByDistinctStudentIDAndDepartmentID();
}
