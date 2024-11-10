package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUser(Long id);
    void deleteUser(Long id);
}
