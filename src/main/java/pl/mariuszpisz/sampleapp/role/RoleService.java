package pl.mariuszpisz.sampleapp.role;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.mariuszpisz.sampleapp.commons.exceptions.ResourceAlreadyExistException;

import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;
import static java.lang.String.format;

@Slf4j
@AllArgsConstructor
@Service
public class RoleService {

    RoleRepository roleRepository;

    public Collection<Role> getAllRoles() {
        var roles = roleRepository.findAll();
        return roles;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = READ_UNCOMMITTED)
    public Role createRole(@Valid Role newRole) {
        checkDuplicate(newRole);

        var role = roleRepository.save(newRole);

        return role;
    }

    private void checkDuplicate(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new ResourceAlreadyExistException(format(
                    "Role with name: %s already exist for system %s", role.getName(), role.getSystem()));
        }
    }
}
