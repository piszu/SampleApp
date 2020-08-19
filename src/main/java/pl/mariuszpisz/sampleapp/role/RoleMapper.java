package pl.mariuszpisz.sampleapp.role;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDTO map(Role source);

    @Mapping(target = "id", ignore = true)
    Role map(RoleDTO source);
}
