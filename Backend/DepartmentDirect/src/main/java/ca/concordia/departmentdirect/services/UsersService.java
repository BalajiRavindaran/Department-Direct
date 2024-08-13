package ca.concordia.departmentdirect.services;

import ca.concordia.departmentdirect.entities.Users;

import java.util.List;

public interface UsersService {
    Users findByEmail(String email);
    Users createUser(Users user);
    Users findByStudentID(String studentID);
    Users findByID(int id);
    List<Users> findByNotificationsContains(String notifications);
    List<Users> findAll();
    Users changePassword(Users users);

}
