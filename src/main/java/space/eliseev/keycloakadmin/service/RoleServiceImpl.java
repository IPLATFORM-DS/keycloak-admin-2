package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.entity.Role;
import space.eliseev.keycloakadmin.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

/**
 * Реализация {@link RoleService}
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    /**
     * Получить список всех ролей
     *
     * @return список всех ролей
     */
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Получить объект роль по идентификатору
     *
     * @param id Идентификатор роли
     * @return роль (или пустой Optional)
     */
    @Override
    public Optional<Role> getById(@NonNull final String id) {
        return roleRepository.findById(id);
    }

    /**
     * Получить роль по имени (может быть список из нескольких ролей при совпадении названий
     * для разных Realm.
     *
     * @param name - название роли (поле name)
     * @return список ролей (названия роли могут совпадать в разных Realm)
     */
    @Override
    public List<Role> getByName(@NonNull final String name) {
        return roleRepository.findByName(name);
    }
}
