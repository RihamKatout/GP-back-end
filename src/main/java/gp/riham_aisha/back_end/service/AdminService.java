package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dta.RegistrationRequest;
import gp.riham_aisha.back_end.model.User;

public interface AdminService {
    User addNewAdmin(RegistrationRequest request);
    User addNewSupport(RegistrationRequest request);
    Boolean deleteSupport(Long id);
}
