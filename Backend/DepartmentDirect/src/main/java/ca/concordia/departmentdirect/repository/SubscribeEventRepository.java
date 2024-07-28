package ca.concordia.departmentdirect.repository;

import ca.concordia.departmentdirect.entities.EventType;
import ca.concordia.departmentdirect.entities.SubscribeEvent;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@JsonSerialize
@JsonNaming
@JsonPOJOBuilder
public interface SubscribeEventRepository extends JpaRepository<SubscribeEvent, Integer> {
}
