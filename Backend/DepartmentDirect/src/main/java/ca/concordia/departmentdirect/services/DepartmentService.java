package ca.concordia.departmentdirect.services;

import ca.concordia.departmentdirect.entities.Department;

public interface DepartmentService {
    Department findDepartmentById(int id);
}
