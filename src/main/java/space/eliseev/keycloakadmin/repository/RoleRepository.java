package space.eliseev.keycloakadmin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.eliseev.keycloakadmin.entity.Role;

import java.util.List;
import java.util.Optional;

/**
 * Получение информации о ролях
 * */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    @Override
    List<Role> findAll();
    @Override
    Optional<Role> findById(String s);
    List<Role> findByName(String name);
}
