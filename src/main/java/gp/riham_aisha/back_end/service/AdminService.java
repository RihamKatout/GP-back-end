package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.model.User;

public interface AdminService {
    User addNewAdmin(RegistrationRequest request);
    User addNewSupport(RegistrationRequest request);
    void deleteSupport(Long id);
    void resetPassword(Long id, String newPassword);
}
