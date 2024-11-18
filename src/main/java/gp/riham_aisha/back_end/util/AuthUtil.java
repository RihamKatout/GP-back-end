package gp.riham_aisha.back_end.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {
    private AuthUtil() {
    }

    public static String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static boolean isCurrentUser(String username) {
        return getCurrentUser().equals(username);
    }
}
