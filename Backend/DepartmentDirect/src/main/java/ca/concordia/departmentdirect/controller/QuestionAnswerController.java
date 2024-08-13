package ca.concordia.departmentdirect.controller;

import ca.concordia.departmentdirect.entities.Department;
import ca.concordia.departmentdirect.entities.QuestionAnswer;
import ca.concordia.departmentdirect.entities.UserEvent;
import ca.concordia.departmentdirect.entities.Users;
import ca.concordia.departmentdirect.entities.dto.*;
import ca.concordia.departmentdirect.services.EmailService;
import ca.concordia.departmentdirect.services.QuestionAnswerService;
import ca.concordia.departmentdirect.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    private final EmailService emailService;

    @GetMapping("listmsg")
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
                        questionAnswerService.getQuestionAnswersByFutureStudentIdandDepartmentID(users.getId(),
                                Integer.parseInt(DepartmentID))));
            }else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("liststudent")
    public ResponseEntity<List<DistinctStudentDepartmentDto>> getallbyDepID(@RequestParam("DepartmentID") String DepartmentID)
    {
        try{
            log.info("getallList=" + DepartmentID);
                return ResponseEntity.ok(
                        questionAnswerService.findByDistinctByDepartmentID(Integer.valueOf(DepartmentID)));
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("listallstudent")
    public ResponseEntity<List<DistinctStudentDepartmentDto>> getdistinctall()
    {
        try{
            return ResponseEntity.ok(
                    questionAnswerService.findByDistinctStudentIDAndDepartmentID());
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("listwithdep")
    public ResponseEntity<List<QuestionAnswerDto>> getall(@RequestParam("StudentID") String StudentID,
                                                                          @RequestParam("DepartmentID") String DepartmentID)
    {
        try{
            log.info("getallList="+ StudentID + "-" + DepartmentID);
            Users users = usersService.findByStudentID(StudentID);
            log.info("userid="+ users);
            if (users!= null)
            {
                return ResponseEntity.ok(QuestionAnswerDto.fromModel(
                        questionAnswerService.getQuestionAnswersByFutureStudentIdandDepartmentID(users.getId(),
                                Integer.parseInt(DepartmentID))));
            }else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }


    @PostMapping("createstudent")
    public ResponseEntity<Map<String, String>> insertStudentMessage(@RequestParam("StudentID") String StudentID,
                                                             @RequestParam("DepartmentID") String DepartmentID,
                                                             @RequestParam("Message") String Message,
                                                             @RequestParam("Category") String Category)
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
                        status("Active").
                        category(Category).
                        build());
                Map<String,String> map = new HashMap<>();
                map.put("Status", "True");
                emailService.sendEmail(users.getEmail(), "DepartmentDirect Notification",
                        "Sending question is successful by ID ="+questionAnswer.getId());
                return ResponseEntity.ok(map);
            }else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Transactional
    @PostMapping("createstaff")
    public ResponseEntity<Map<String, String>> insertStaffMessage(@RequestParam("StudentID") String StudentID,
                                                                  @RequestParam("StaffID") String StaffID,
                                                                    @RequestParam("DepartmentID") String DepartmentID,
                                                                    @RequestParam("Message") String Message,
                                                                    @RequestParam("Category") String Category)
    {
        try{
            log.info("getallList="+ StaffID + "-" + DepartmentID);
            Users users = usersService.findByID(Integer.parseInt(StaffID));
            Users student = usersService.findByStudentID(StudentID);
            log.info("userid="+ users);
            if (users!= null && student != null)
            {
                QuestionAnswer questionAnswer = questionAnswerService.createQuestionAnswer(QuestionAnswer.builder().
                        futureStudent(student).
                        staff(users).
                        department(Department.builder().id(Integer.valueOf(DepartmentID)).build()).
                        message(Message).
                        datetime(new Date()).
                        status("Inactive").
                        category(Category).
                        build());
                questionAnswerService.UpdateStudentStatus(Integer.valueOf(StudentID),
                        Integer.valueOf(DepartmentID));
                Map<String,String> map = new HashMap<>();
                map.put("Status", "True");
                emailService.sendEmail(users.getEmail(), "DepartmentDirect Notification",
                        "Sending question is successful by ID ="+questionAnswer.getId());
                return ResponseEntity.ok(map);
            }else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("categoryperc")
    public ResponseEntity<List<CategoryPercentageDto>> getCategoryPercentage()
    {
        try{
            return ResponseEntity.ok(
                    questionAnswerService.getCategoryPercentage());
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("categorypercbydep")
    public ResponseEntity<List<CategoryPercentageByDepDto>> getCategoryPercentageByDep()
    {
        try{
            return ResponseEntity.ok(
                    questionAnswerService.getCategoryPercentageByDepartment());
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }


}
