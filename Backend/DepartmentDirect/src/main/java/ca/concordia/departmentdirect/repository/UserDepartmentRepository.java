package ca.concordia.departmentdirect.repository;

import ca.concordia.departmentdirect.entities.SubscribeEvent;
import ca.concordia.departmentdirect.entities.UserDepartment;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JsonSerialize
@JsonNaming
@JsonPOJOBuilder
public interface UserDepartmentRepository extends JpaRepository<UserDepartment, Integer> {
}
