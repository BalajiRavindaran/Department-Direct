package ca.concordia.departmentdirect.services;

import ca.concordia.departmentdirect.entities.UserEvent;
import ca.concordia.departmentdirect.entities.Users;
import ca.concordia.departmentdirect.repository.UserEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEventServiceImpl implements UserEventService{
    private final UserEventRepository userEventRepository;

    @Override
    public UserEvent findUserEventById(int id) {
        return userEventRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserEvent> findUserEventsByUser(int userId) {
        return userEventRepository.findByUsers(Users.builder().id(userId).build());
    }
}
