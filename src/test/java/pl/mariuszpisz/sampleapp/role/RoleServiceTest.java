package pl.mariuszpisz.sampleapp.role;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mariuszpisz.sampleapp.commons.exceptions.ResourceAlreadyExistException;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static pl.mariuszpisz.sampleapp.utils.RoleTestUtils.*;


@Import({RoleService.class})
@ExtendWith(SpringExtension.class)
class RoleServiceTest {

    @MockBean
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @Test
    void shouldReturnRoleWithId() {
        //given
        var expectedRole = anyRole(ANY_ROLE_ID);

        when(roleRepository.findAll()).thenReturn(
                singletonList(anyRole(ANY_ROLE_ID)));

        //when
        var resultRoles = roleService.getAllRoles();

        //then
        assertThat(resultRoles, notNullValue());
        assertThat(resultRoles, not(emptyCollectionOf(Role.class)));
    }

    @Test
    void shouldCreateWhenRoleExistsWithOtherSystem() {
        //given
        var newRole = anyRole(1L);
        var newRoleOtherSystem = anyRole(2L);
        newRoleOtherSystem.setSystem(ANY_OTHER_SYSTEM_NAME);

        when(roleRepository.existsByNameAndSystem(newRole.getName(), newRole.getSystem()))
                .thenReturn(false);
        when(roleRepository.existsByNameAndSystem(newRoleOtherSystem.getName(), newRoleOtherSystem.getSystem()))
                .thenReturn(false);
        when(roleRepository.save(newRole))
                .thenReturn(newRole);
        when(roleRepository.save(newRoleOtherSystem))
                .thenReturn(newRoleOtherSystem);

        //when
        var role = roleService.createRole(newRole);
        //then
        assertThat(role.getId(), is(1L));
        assertThat(role.getName(), is(ANY_ROLE_NAME));
        assertThat(role.getSystem(), is(ANY_SYSTEM_NAME));

        //when
        var roleWithOtherSystem = roleService.createRole(newRoleOtherSystem);
        //then
        assertThat(roleWithOtherSystem.getId(), is(2L));
        assertThat(roleWithOtherSystem.getName(), is(ANY_ROLE_NAME));
        assertThat(roleWithOtherSystem.getSystem(), is(ANY_OTHER_SYSTEM_NAME));
    }

    @Test
    void shouldThrowOnCreateWhenSameRoleExist() {
        //given
        var newRole = anyRole();

        when(roleRepository.existsByNameAndSystem(newRole.getName(), newRole.getSystem()))
                .thenReturn(true);

        //when
        var exception = assertThrows(ResourceAlreadyExistException.class,
                () -> roleService.createRole(newRole));

        //then
        assertThat(exception.getMessage(), not(blankOrNullString()));
    }


}