package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.model.Store;
import gp.riham_aisha.back_end.model.User;

import java.util.List;

public interface AdminService {
    List<Store> getStores();
    List<User> getAdminsAndSupports();
    User addNewAdmin(Long userId);
    User addNewSupport(Long userId);
    void deleteSupport(Long id);
    void resetPassword(Long id, String newPassword);
}
