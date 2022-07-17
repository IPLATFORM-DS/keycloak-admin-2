package space.eliseev.keycloakadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.eliseev.keycloakadmin.entity.Role;

import java.util.List;

/**
 * Получение информации о ролях
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findByName(String name);
}
