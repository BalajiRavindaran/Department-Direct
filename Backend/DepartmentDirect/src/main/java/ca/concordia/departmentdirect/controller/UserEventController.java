package ca.concordia.departmentdirect.controller;

import ca.concordia.departmentdirect.entities.UserEvent;
import ca.concordia.departmentdirect.entities.Users;
import ca.concordia.departmentdirect.entities.dto.UserEventDto;
import ca.concordia.departmentdirect.services.UserEventService;
import ca.concordia.departmentdirect.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("userevent")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserEventController {
    private final UserEventService userEventService;
    private final UsersService usersService;

    @GetMapping("list")
    public ResponseEntity<List<UserEventDto>> getallEventList(String email)
    {
        try{
            log.info("getallEventList="+ email);
            Users users = usersService.findByEmail(email);
            log.info("userid="+ users.getId());
            List<UserEvent> userEventList = userEventService.findUserEventsByUser(users.getId());
            return ResponseEntity.ok(UserEventDto.fromModel(userEventList));
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("getdatail")
    public ResponseEntity<UserEvent> getDetailEvent(int eventId)
    {
        try{
            log.info("getallEventList="+ eventId);
            UserEvent userEvent = userEventService.findUserEventById(eventId);
            return ResponseEntity.ok(userEvent);
        }catch (Exception er)
        {
            log.error(er.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }


}
