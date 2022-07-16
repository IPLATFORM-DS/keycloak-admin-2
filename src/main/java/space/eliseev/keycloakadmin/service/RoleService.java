package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import space.eliseev.keycloakadmin.entity.Role;

import java.util.List;
import java.util.Optional;

/**
 * Получение информации о ролях
 */
public interface RoleService {
    List<Role> getAllRoles();

    Optional<Role> getById(@NonNull String id);

    List<Role> getByName(@NonNull String name);
}
