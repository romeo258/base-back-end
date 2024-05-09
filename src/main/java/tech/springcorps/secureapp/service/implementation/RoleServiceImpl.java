package tech.springcorps.secureapp.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.springcorps.secureapp.domain.Role;
import tech.springcorps.secureapp.repository.RoleRepository;
import tech.springcorps.secureapp.service.RoleService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository<Role> roleRoleRepository;

    @Override
    public Role getRoleByUserId(Long id) {
        return roleRoleRepository.getRoleByUserId(id);
    }

    @Override
    public Collection<Role> getRoles() {
        return roleRoleRepository.list();
    }
}
