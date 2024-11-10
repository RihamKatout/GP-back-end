package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.repository.UserRepository;
import gp.riham_aisha.back_end.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
