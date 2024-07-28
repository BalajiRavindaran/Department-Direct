package ca.concordia.departmentdirect.services;

import ca.concordia.departmentdirect.entities.Users;

public interface UsersService {
    Users findByEmail(String email);
    Users createUser(Users user);
    Users findByStudentID(String studentID);
}
