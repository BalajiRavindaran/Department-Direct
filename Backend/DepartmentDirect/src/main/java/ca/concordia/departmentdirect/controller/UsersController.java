package ca.concordia.departmentdirect.controller;

import ca.concordia.departmentdirect.entities.UserEvent;
import ca.concordia.departmentdirect.entities.Users;
import ca.concordia.departmentdirect.entities.dto.UserEventDto;
import ca.concordia.departmentdirect.entities.dto.UsersDto;
import ca.concordia.departmentdirect.services.EmailService;
import ca.concordia.departmentdirect.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("users")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UsersService usersService;
    private final EmailService emailService;

    @PostMapping("create")
    public ResponseEntity<Map<String, String>> createUser(@RequestParam("Fullname") String Fullname,
                                                           @RequestParam("DateOfBirth") String DateOfBirth,
                                                           @RequestParam("Email") String Email,
                                                           @RequestParam("Notifications") String Notifications,
                                                           @RequestParam("StudentID") String StudentID,
                                                           @RequestParam("Password") String Password
                                              )
    {
        try{
            log.info("getallList="+ Email +"-"+ Fullname + "-" + DateOfBirth + "-" + Notifications + "-" + Password + "-" + StudentID);
            Users users = usersService.findByEmail(Email);
             if (users !=  null && users.getId()> 0)
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            else {
                if (Email.isEmpty() || Password.length() < 4 || Fullname.isEmpty())
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                Users u = usersService.createUser(Users.builder().email(Email).
                        password(Password).
                        notifications(Notifications).
                        studentId(StudentID).
                        fullname(Fullname).
                        dateOfBirth(DateOfBirth).
                        role("Student").build());
                log.info("new user="+ u.toString());
                 Map<String,String> map = new HashMap<>();
                 map.put("Status", Boolean.valueOf(u.getId() > 0).toString());
                return ResponseEntity.ok(map);
            }
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("login")
    public ResponseEntity<UsersDto> login(String studentid, String password)
    {
        try{
            log.info("getallEventList="+ studentid);
            Users users = usersService.findByStudentID(studentid);
            if (users !=  null && users.getId() > 0) {
                log.info("userid=" + users.getId());
                if (users.getPassword().equals(password))
                    return ResponseEntity.ok(UsersDto.fromModel(users));
                else
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
            else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @GetMapping("loginstaff")
    public ResponseEntity<Map<String, String>> loginStaff(String ID, String Password, String Role)
    {
        try{
            log.info("getallEventList="+ ID);
            Users users = usersService.findByID(Integer.parseInt(ID));
            if (users !=  null && users.getId() > 0) {
                log.info("userid=" + users.getId());
                if (users.getPassword().equals(Password) && users.getRole().equals(Role)
                        && !users.getRole().equals("Student")) {
                    Map<String,String> map = new HashMap<>();
                    map.put("Status", Boolean.valueOf(users.getId() > 0).toString());
                    map.put("Role", Role);
                    return ResponseEntity.ok(map);
                }
                else
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
            else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PostMapping("changepass")
    public ResponseEntity<Map<String, String>> changePass(@RequestParam("StudentID") String StudentID,
                                                          @RequestParam("Email") String Email,
                                                          @RequestParam("Password") String Password,
                                                          @RequestParam("NewPassword") String NewPassword
    )
    {
        try{
            log.info("getallList="+ Email +"-"+ StudentID + "-" + Email + "-" + NewPassword);
            Users users = usersService.findByEmail(Email);
            if (users !=  null && users.getId()> 0
                    && users.getStudentId().equals(StudentID) && users.getPassword().equals(Password)) {
                users.setPassword(NewPassword);
                usersService.changePassword(users);
                Map<String,String> map = new HashMap<>();
                map.put("Status", "True");
                emailService.sendEmail(users.getEmail(), "Change Password",
                        "Your password has been changed " );
                return ResponseEntity.ok(map);

            }else
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);

        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }


}
