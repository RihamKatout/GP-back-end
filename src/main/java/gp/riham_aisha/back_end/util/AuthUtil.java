package gp.riham_aisha.back_end.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
    private AuthUtil() {
    }

    public static String getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null ? authentication.getName() : "System";
    }

    public static boolean isCurrentUser(String username) {
        return getCurrentUser().equals(username);
    }
}
