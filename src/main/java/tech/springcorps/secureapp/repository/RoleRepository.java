package tech.springcorps.secureapp.repository;

import org.springframework.stereotype.Repository;
import tech.springcorps.secureapp.domain.Role;

import java.util.Collection;

@Repository
public interface RoleRepository <T extends Role> {
    /*Basic CRUD Operation */
    T create(T data);
//    Collection<T> list(int page, int pageSize);
    Collection<T> list();
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    /* More Complex Operation */
    void addRoleToUser(Long userId, String roleName);
    Role getRoleByUserId(Long userId);
    Role getRoleByUserEmail(String email);
    void updateUserRole(Long userId, String roleName);

}
