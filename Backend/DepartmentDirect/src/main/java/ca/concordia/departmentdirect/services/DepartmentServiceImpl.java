package ca.concordia.departmentdirect.services;

import ca.concordia.departmentdirect.entities.Department;
import ca.concordia.departmentdirect.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;


    @Override
    public Department findDepartmentById(int id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
