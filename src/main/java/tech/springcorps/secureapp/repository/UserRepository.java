package tech.springcorps.secureapp.repository;

import org.springframework.web.multipart.MultipartFile;
import tech.springcorps.secureapp.domain.User;
import tech.springcorps.secureapp.dto.UserDTO;
import tech.springcorps.secureapp.form.UpdateForm;

import java.util.Collection;

/**
 * @author Spring Coprs (https://www.springcorps.tech)
 * @version 1.0
 * @since 01/02/2024
 */

public interface UserRepository<T extends User> {
    /*Basic CRUD Operation */
    /* Basic CRUD Operations */
    T create(T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    /* More Complex Operations */
    User getUserByEmail(String email);
    void sendVerificationCode(UserDTO user);
    User verifyCode(String email, String code);
    void resetPassword(String email);
    T verifyPasswordKey(String key);
    void renewPassword(String key, String password, String confirmPassword);
    void renewPassword(Long userId, String password, String confirmPassword);
    T verifyAccountKey(String key);
    T updateUserDetails(UpdateForm user);
    void updatePassword(Long id, String currentPassword, String newPassword, String confirmNewPassword);
    void updateAccountSettings(Long userId, Boolean enabled, Boolean notLocked);
    User toggleMfa(String email);
    void updateImage(UserDTO user, MultipartFile image);
}
