package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.enums.Role;
import gp.riham_aisha.back_end.exception.ValidationException;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.service.AdminService;
import gp.riham_aisha.back_end.service.AuthenticationService;
import gp.riham_aisha.back_end.service.UserService;
import gp.riham_aisha.back_end.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Override
    public User addNewAdmin(RegistrationRequest request) {
        User newAdmin = (User) authenticationService.register(request, Role.ADMIN, Role.SUPPORT, Role.CUSTOMER).user();
        log.info("Adding a new admin with username: {} by: {}", newAdmin.getUsername(), AuthUtil.getCurrentUser());
        return newAdmin;
    }

    @Override
    public User addNewSupport(RegistrationRequest request) {
        User newSupport = (User) authenticationService.register(request, Role.SUPPORT, Role.CUSTOMER).user();
        log.info("Adding a new support with username: {} by: {}", newSupport.getUsername(), AuthUtil.getCurrentUser());
        return newSupport;
    }

    @Override
    public void deleteSupport(Long id) {
        User support = userService.getUser(id).orElseThrow();
        Role[] userRoles = support.getRoles();
        int count = 0;
        for (var role : userRoles) {
            if (role.equals(Role.SUPPORT)) {
                count++;
            }
            if (role.equals(Role.ADMIN)) {
                count++;
            }
        }
        if (count == 1) {
            userService.deleteUser(id);
            log.info("Deleting support with id: {} by: {}", id, AuthUtil.getCurrentUser());
            return;
        }
        throw new ValidationException(Map.of("error", "User is not a support"));
    }

    @Override
    public void resetPassword(Long id, String newPassword) {
        userService.resetPassword(id, newPassword);
        log.info("Resetting password for user with id: {} by: {}", id, AuthUtil.getCurrentUser());
    }
}
