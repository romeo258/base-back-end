package tech.springcorps.secureapp.service;

import tech.springcorps.secureapp.domain.Role;

import java.util.Collection;

public interface RoleService {

    Role getRoleByUserId(Long id);

    Collection<Role> getRoles();
}
