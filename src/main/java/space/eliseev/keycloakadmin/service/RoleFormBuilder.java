package space.eliseev.keycloakadmin.service;

import space.eliseev.keycloakadmin.dto.RoleDto;

import java.util.List;

public interface RoleFormBuilder {
    byte[] download(List<RoleDto> list);
}
