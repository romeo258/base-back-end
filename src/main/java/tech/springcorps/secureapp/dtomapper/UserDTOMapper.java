package tech.springcorps.secureapp.dtomapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tech.springcorps.secureapp.domain.Role;
import tech.springcorps.secureapp.domain.User;
import tech.springcorps.secureapp.dto.UserDTO;


public class UserDTOMapper {
    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    public static UserDTO fromUser(User user, Role role) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setRoleName(role.getName());
        userDTO.setPermissions(role.getPermission());
        return userDTO;
    }

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }
}
















