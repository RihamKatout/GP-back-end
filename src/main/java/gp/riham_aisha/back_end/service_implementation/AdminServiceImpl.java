package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.enums.Role;
import gp.riham_aisha.back_end.exception.ValidationException;
import gp.riham_aisha.back_end.model.Store;
import gp.riham_aisha.back_end.model.User;
import gp.riham_aisha.back_end.service.AdminService;
import gp.riham_aisha.back_end.service.AuthenticationService;
import gp.riham_aisha.back_end.service.StoreService;
import gp.riham_aisha.back_end.service.UserService;
import gp.riham_aisha.back_end.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final StoreService storeService;

    @Override
    public List<Store> getStores() {
        return storeService.getAllStores();
    }

    @Override
    public List<User> getAdminsAndSupports() {
        return userService.getAdminsAndSupports();
    }

    @Override
    public User addNewAdmin(Long userId) {
        User newAdmin = userService.addRoles(userId, Role.ADMIN, Role.SUPPORT);
        log.info("Adding a new admin with username: {} by: {}", newAdmin.getUsername(), AuthUtil.getCurrentUser());
        return newAdmin;
    }

    @Override
    public User addNewSupport(Long userId) {
        User newSupport = userService.addRoles(userId, Role.SUPPORT);
        log.info("Adding a new support with username: {} by: {}", newSupport.getUsername(), AuthUtil.getCurrentUser());
        return newSupport;
    }

    @Override
    public void deleteSupport(Long id) {
        User support = userService.getUser(id);
        if (Boolean.FALSE.equals(support.hasRole(Role.ADMIN))
                && Boolean.TRUE.equals(support.hasRole(Role.SUPPORT))) {
            userService.removeRole(id, Role.SUPPORT);
            log.info("Removing support with id: {} by: {}", id, AuthUtil.getCurrentUser());
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
