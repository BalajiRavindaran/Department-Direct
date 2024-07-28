package ca.concordia.departmentdirect.services;

import ca.concordia.departmentdirect.entities.Users;
import ca.concordia.departmentdirect.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService{
    private final UsersRepository usersRepository;

    @Override
    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    @Override
    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public Users findByStudentID(String studentID) {
        return usersRepository.findByStudentId(studentID);
    }
}
