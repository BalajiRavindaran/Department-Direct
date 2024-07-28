package ca.concordia.departmentdirect.controller;

import ca.concordia.departmentdirect.entities.UserEvent;
import ca.concordia.departmentdirect.entities.Users;
import ca.concordia.departmentdirect.entities.dto.UserEventDto;
import ca.concordia.departmentdirect.entities.dto.UsersDto;
import ca.concordia.departmentdirect.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("users")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UsersService usersService;

    @PostMapping("create")
    public ResponseEntity<Boolean> createUser(@RequestParam("Fullname") String Fullname,
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
                        isstudent(true).build());
                log.info("new user="+ u.toString());
                return ResponseEntity.ok(u.getId() > 0);
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


}
