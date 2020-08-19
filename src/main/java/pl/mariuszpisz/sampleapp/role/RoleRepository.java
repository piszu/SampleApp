package pl.mariuszpisz.sampleapp.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Long> {
    boolean existsByNameAndSystem(String name, String system);

    boolean existsByName(String name);

}
