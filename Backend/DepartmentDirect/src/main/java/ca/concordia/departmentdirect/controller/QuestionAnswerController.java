package ca.concordia.departmentdirect.controller;

import ca.concordia.departmentdirect.entities.Department;
import ca.concordia.departmentdirect.entities.QuestionAnswer;
import ca.concordia.departmentdirect.entities.UserEvent;
import ca.concordia.departmentdirect.entities.Users;
import ca.concordia.departmentdirect.entities.dto.QuestionAnswerDto;
import ca.concordia.departmentdirect.entities.dto.UserEventDto;
import ca.concordia.departmentdirect.services.QuestionAnswerService;
import ca.concordia.departmentdirect.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("qa")
@RestController
@RequiredArgsConstructor
@Slf4j
public class QuestionAnswerController {
    private final QuestionAnswerService questionAnswerService;
    private final UsersService usersService;

    @GetMapping("list")
    public ResponseEntity<List<QuestionAnswerDto>> getallbyuserIDandDepID(@RequestParam("StudentID") String StudentID,
                                                                          @RequestParam("DepartmentID") String DepartmentID)
    {
        try{
            log.info("getallList="+ StudentID + "-" + DepartmentID);
            Users users = usersService.findByStudentID(StudentID);
            log.info("userid="+ users);
            if (users!= null)
            {
                return ResponseEntity.ok(
                        QuestionAnswerDto.fromModel(
                        questionAnswerService.getQuestionAnswersByFutureStudentIdandDepartmentID(users.getId(), Integer.parseInt(DepartmentID))));
            }else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("listwithdep")
    public ResponseEntity<List<QuestionAnswer>> getall(@RequestParam("StudentID") String StudentID,
                                                                          @RequestParam("DepartmentID") String DepartmentID)
    {
        try{
            log.info("getallList="+ StudentID + "-" + DepartmentID);
            Users users = usersService.findByStudentID(StudentID);
            log.info("userid="+ users);
            if (users!= null)
            {
                return ResponseEntity.ok(
                        questionAnswerService.getQuestionAnswersByFutureStudentIdandDepartmentID(users.getId(), Integer.parseInt(DepartmentID)));
            }else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }


    @PostMapping("create")
    public ResponseEntity<Map<String, String>> insertMessage(@RequestParam("StudentID") String StudentID,
                                                             @RequestParam("DepartmentID") String DepartmentID,
                                                             @RequestParam("Message") String Message)
    {
        try{
            log.info("getallList="+ StudentID + "-" + DepartmentID);
            Users users = usersService.findByStudentID(StudentID);
            log.info("userid="+ users);
            if (users!= null)
            {
                QuestionAnswer questionAnswer = questionAnswerService.createQuestionAnswer(QuestionAnswer.builder().
                        staff(null).
                        futureStudent(users).
                        department(Department.builder().id(Integer.valueOf(DepartmentID)).build()).
                        message(Message).
                        datetime(new Date()).
                        build());
                Map<String,String> map = new HashMap<>();
                map.put("Status", "True");
                return ResponseEntity.ok(map);
            }else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
