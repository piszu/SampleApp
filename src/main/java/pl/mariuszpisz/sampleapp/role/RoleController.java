package pl.mariuszpisz.sampleapp.role;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;
import static pl.mariuszpisz.sampleapp.role.RoleController.ROLE_URL;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = ROLE_URL, produces = APPLICATION_JSON_VALUE)
public class RoleController {
    public final static String ROLE_URL = "/api/roles";

    RoleService roleService;
    RoleMapper roleMapper;

    @GetMapping
    ResponseEntity<Collection<RoleDTO>> getAllRoles() {
        log.info("Request to get list of all roles");
        var roles = roleService.getAllRoles().stream()
                .map(roleMapper::map)
                .collect(toList());

        return ok().body(roles);
    }

    @PostMapping
    ResponseEntity<Void> createRole(@RequestBody @Valid RoleDTO newRole) {
        var role = roleService.createRole(
                roleMapper.map(newRole));

        return created(resourceLocation(role.getId())).build();
    }

    private URI resourceLocation(Long id) {
        return fromCurrentContextPath()
                .path(ROLE_URL)
                .path("/{id}").buildAndExpand(id).toUri();
    }
}
