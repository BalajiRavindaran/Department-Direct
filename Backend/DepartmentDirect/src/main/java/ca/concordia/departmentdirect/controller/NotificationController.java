package ca.concordia.departmentdirect.controller;

import ca.concordia.departmentdirect.entities.Notifications;
import ca.concordia.departmentdirect.entities.Users;
import ca.concordia.departmentdirect.entities.dto.UsersDto;
import ca.concordia.departmentdirect.services.DepartmentService;
import ca.concordia.departmentdirect.services.EmailService;
import ca.concordia.departmentdirect.services.NotificationsService;
import ca.concordia.departmentdirect.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("nt")
@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationsService notificationsService;
    private final UsersService usersService;
    private final  DepartmentService departmentService;
    private final EmailService emailService;

    @PostMapping("send")
    public ResponseEntity<Map<String, String>> sendNotifications(@RequestParam("AdminID") String AdminID,
                                                                    @RequestParam("Message") String Message,
                                                                    @RequestParam("Types") String Types)
    {
        try {
            if (!Types.isEmpty()) {
                notificationsService.createNotification(Notifications.builder()
                        .types(Types).admin(usersService.findByID(Integer.parseInt(AdminID)))
                        .message(Message).build());
                if (Types.contains("All"))
                {
                    log.info("All notifications ");
                    List<Users> usersList = usersService.findAll();
                    for (Users user : usersList) {
                        emailService.sendEmail(user.getEmail(), "Notification : " , Message);
                    }

                }else {
                    String[] typeList = Types.split(",");
                    for (String type : typeList) {
                        List<Users> usersList = usersService.findByNotificationsContains(type.trim());
                        for (Users user : usersList) {
                            emailService.sendEmail(user.getEmail(), "Notification about " + type, Message);
                        }
                    }
                }
                Map<String,String> map = new HashMap<>();
                map.put("Status", "True");
                return ResponseEntity.ok(map);
            }
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("unsubscribe")
    public ResponseEntity<Map<String, String>> unsubscribe(String studentid, String notification)
    {
        try{

            log.info("getallEventList="+ studentid);
            Users users = usersService.findByStudentID(studentid);
            if (users !=  null && users.getId() > 0) {
                log.info("userid=" + users.getId());
                String note = users.getNotifications();
                note = users.getNotifications().replaceAll(notification+",", "");
                note = note.replaceAll(notification, "");
                users.setNotifications(note);
                usersService.changePassword(users);
                Map<String,String> map = new HashMap<>();
                map.put("Status", "True");
                return ResponseEntity.ok(map);
            }
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

}
