package space.eliseev.keycloakadmin.service;

import space.eliseev.keycloakadmin.entity.Role;

import java.util.List;
import java.util.Optional;

/**
    * Получение информации о ролях
 */
public interface RoleService {
    List<Role> findAll();
    Optional<Role> findById(String s);
    List<Role> findByName(String name);
}
