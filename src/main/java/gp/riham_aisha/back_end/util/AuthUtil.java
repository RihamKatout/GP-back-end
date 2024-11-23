package gp.riham_aisha.back_end.util;

import gp.riham_aisha.back_end.enums.Role;
import gp.riham_aisha.back_end.model.Store;
import gp.riham_aisha.back_end.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;
import java.util.stream.Collectors;

public class AuthUtil {
    private AuthUtil() {
    }

    public static String getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : "System";
    }

    public static Boolean doesCurrentUserHasAuthority(Role role){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet())
                .contains(role.name());
    }

    private static boolean isCurrentUser(String username) {
        return getCurrentUser().equals(username);
    }

    public static User validateStoreOwner(Store store) {
        User manager = store.getManager();
        // check if the current user is the manager of the store
        if (!AuthUtil.isCurrentUser(manager.getUsername())) {
            throw new SecurityException("You are not authorized to update this store information or products");
        }
        return manager;
    }
}
