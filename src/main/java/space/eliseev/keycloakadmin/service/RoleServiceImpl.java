package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.ClientDto;
import space.eliseev.keycloakadmin.dto.RoleDto;
import space.eliseev.keycloakadmin.entity.Realm;
import space.eliseev.keycloakadmin.mapper.RoleMapper;
import space.eliseev.keycloakadmin.repository.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Реализация {@link RoleService}
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final ClientService clientService;
    private final RealmService realmService;

    /**
     * Получить список всех ролей
     *
     * @return список всех ролей
     */
    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(role -> {
            Optional<ClientDto> client = clientService.getById(role.getId());
            Optional<Realm> realm = realmService.getById(role.getRealmId());
            String clientName = client.map(ClientDto::getName).orElse(null);
            String realmName = realm.map(Realm::getName).orElse(null);
            RoleDto dto = roleMapper.roleToRoleDto(role);
            dto.setRealmName(realmName);
            dto.setRealmName(clientName);
            return dto;
        })
                .collect(Collectors.toList());
    }

    /**
     * Получить объект роль по идентификатору
     *
     * @param id Идентификатор роли
     * @return роль (или пустой Optional)
     */
    @Override
    public Optional<RoleDto> getById(@NonNull final String id) {

        return Optional.ofNullable(roleMapper.roleToRoleDto(roleRepository.findById(id)
                .orElse(null)));
    }

    /**
     * Получить роль по имени (может быть список из нескольких ролей при совпадении названий
     * для разных Realm.
     *
     * @param name - название роли (поле name)
     * @return список ролей (названия роли могут совпадать в разных Realm)
     */
    @Override
    public List<RoleDto> getByName(@NonNull final String name) {
        return roleRepository.findByName(name)
                .stream()
                .map(roleMapper::roleToRoleDto)
                .collect(Collectors.toList());
    }
}
