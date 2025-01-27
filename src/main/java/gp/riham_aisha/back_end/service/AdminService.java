package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.model.Store;
import gp.riham_aisha.back_end.model.User;

import java.util.List;

public interface AdminService {
    List<Store> getStores();
    User addNewAdmin(RegistrationRequest request);
    User addNewSupport(RegistrationRequest request);
    void deleteSupport(Long id);
    void resetPassword(Long id, String newPassword);
}
