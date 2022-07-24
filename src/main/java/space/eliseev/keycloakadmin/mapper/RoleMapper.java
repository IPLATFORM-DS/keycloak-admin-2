package space.eliseev.keycloakadmin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import space.eliseev.keycloakadmin.dto.RoleDto;
import space.eliseev.keycloakadmin.entity.Client;
import space.eliseev.keycloakadmin.entity.Realm;
import space.eliseev.keycloakadmin.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "clientName", source = "client.clientId")
    @Mapping(target = "realmName", source = "realm.name")
    RoleDto roleToRoleDto(Role role, Client client, Realm realm);
}
