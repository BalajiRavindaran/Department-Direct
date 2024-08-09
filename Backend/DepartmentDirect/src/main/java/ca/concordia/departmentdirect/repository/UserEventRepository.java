package ca.concordia.departmentdirect.repository;

import ca.concordia.departmentdirect.entities.UserDepartment;
import ca.concordia.departmentdirect.entities.UserEvent;
import ca.concordia.departmentdirect.entities.Users;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JsonSerialize
@JsonNaming
@JsonPOJOBuilder
public interface UserEventRepository extends JpaRepository<UserEvent, Integer> {
    List<UserEvent> findByUsers(Users users);
}
