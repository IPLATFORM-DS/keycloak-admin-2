package space.eliseev.keycloakadmin.service;

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

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

}
