package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import space.eliseev.keycloakadmin.dto.RoleDto;

import java.util.List;
import java.util.Optional;

/**
 * Получение информации о ролях
 */
public interface RoleService {
    List<RoleDto> getAllRoles();

    Optional<RoleDto> getById(@NonNull String id);

    List<RoleDto> getByName(@NonNull String name);
}
