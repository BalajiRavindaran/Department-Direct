package ca.concordia.departmentdirect.services;

import ca.concordia.departmentdirect.entities.SubscribeEvent;
import ca.concordia.departmentdirect.repository.SubscribeEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscribeEventServiceImpl implements SubscribeEventService {
    private final SubscribeEventRepository subscribeEventRepository;

    @Override
    public SubscribeEvent findSubscribeEventById(int id) {
        return subscribeEventRepository.findById(id).orElse(null);
    }
}
