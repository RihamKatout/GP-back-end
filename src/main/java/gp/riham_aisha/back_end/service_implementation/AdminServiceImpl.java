package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.enums.Role;
import gp.riham_aisha.back_end.exception.ValidationException;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.service.AdminService;
import gp.riham_aisha.back_end.service.AuthenticationService;
import gp.riham_aisha.back_end.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @Override
    public User addNewAdmin(RegistrationRequest request) {
        return (User) authenticationService.register(request, Role.ADMIN).user();
    }

    @Override
    public User addNewSupport(RegistrationRequest request) {
        return (User) authenticationService.register(request, Role.SUPPORT).user();
    }

    @Override
    public void deleteSupport(Long id) {
        User support = userService.getUser(id).orElseThrow();
        Role[] userRoles = support.getRoles();
        for (var role : userRoles) {
            if (role.equals(Role.SUPPORT)) {
                userService.deleteUser(id);
                return;
            }
        }
        throw new ValidationException(Map.of("error", "User is not a support"));
    }

    @Override
    public void resetPassword(Long id, String newPassword) {
        userService.resetPassword(id, newPassword);
    }
}
