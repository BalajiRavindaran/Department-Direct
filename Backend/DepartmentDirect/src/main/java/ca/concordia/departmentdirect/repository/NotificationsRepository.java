package ca.concordia.departmentdirect.repository;

import ca.concordia.departmentdirect.entities.Notifications;
import ca.concordia.departmentdirect.entities.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notifications, Integer> {
}
