package pl.mariuszpisz.sampleapp.utils;

import pl.mariuszpisz.sampleapp.role.Role;
import pl.mariuszpisz.sampleapp.role.RoleDTO;

import java.util.Random;

public class RoleTestUtils {
    public final static String ANY_ROLE_NAME = "ANY_ROLE_NAME";
    public final static String ANY_OTHER_ROLE_NAME = "ANY_OTHER_ROLE_NAME";
    public final static String ANY_SYSTEM_NAME = "ANY_SYSTEM_NAME";
    public final static String ANY_OTHER_SYSTEM_NAME = "ANY_OTHER_SYSTEM_NAME";

    private final static Random random = new Random();
    public final static Long ANY_ROLE_ID = random.nextLong();

    public static Role.RoleBuilder anyRoleBuilder() {
        return Role.builder()
                .name(ANY_ROLE_NAME)
                .system(ANY_SYSTEM_NAME);
    }

    public static Role anyRole() {
        return anyRoleBuilder().build();
    }

    public static Role anyRole(Long id) {
        return (Role) anyRoleBuilder().id(id).build();
    }

    public static RoleDTO anyRoleDTO() {
        return RoleDTO.of(null, ANY_ROLE_NAME, ANY_SYSTEM_NAME);
    }

    public static RoleDTO anyRoleDTO(Long id) {
        return RoleDTO.of(id, ANY_ROLE_NAME, ANY_SYSTEM_NAME);
    }

}
