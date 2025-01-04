package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.model.User;

public interface UserService {
    User getUser(Long id);

    void deleteUser(Long id);

    void resetPassword(Long id, String newPassword);

    User getUserByUsername(String username);
}
