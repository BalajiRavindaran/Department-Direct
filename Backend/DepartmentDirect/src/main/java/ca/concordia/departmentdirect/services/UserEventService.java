package ca.concordia.departmentdirect.services;

import ca.concordia.departmentdirect.entities.UserEvent;

import java.util.List;

public interface UserEventService {
    UserEvent findUserEventById(int id);
    List<UserEvent> findUserEventsByUser(int userId);
}
