package tech.springcorps.secureapp.utils;

import org.springframework.security.core.Authentication;
import tech.springcorps.secureapp.domain.UserPrincipal;
import tech.springcorps.secureapp.dto.UserDTO;

public class UserUtils {
    public static UserDTO getAuthenticatedUser(Authentication authentication) {
        return ((UserDTO) authentication.getPrincipal());
    }

    public static UserDTO getLoggedInUser(Authentication authentication) {
        return ((UserPrincipal) authentication.getPrincipal()).getUser();
    }

}
