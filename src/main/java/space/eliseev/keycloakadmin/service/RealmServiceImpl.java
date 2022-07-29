package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.RealmDto;
import space.eliseev.keycloakadmin.mapper.RealmMapper;
import space.eliseev.keycloakadmin.repository.RealmRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RealmServiceImpl implements RealmService {

    private final RealmRepository realmRepository;
    private final RealmMapper realmMapper;

    /**
     * Получить список всех реалмов
     *
     * @return список всех реалмов
     */
    @Override
    public List<RealmDto> getAllRealms() {
        return realmRepository.findAll()
                .stream()
                .map(realmMapper::realmToRealmDto)
                .collect(Collectors.toList());
    }

    /**
     * Получить объект реалм по идентификатору
     *
     * @param id Идентификатор реалма
     * @return реалм (или пустой Optional)
     */
    @Override
    public Optional<RealmDto> getById(@NonNull final String id) {
        return Optional.ofNullable(realmMapper.realmToRealmDto(realmRepository.findById(id)
                .orElse(null)));
    }

    /**
     * Получить объект реалм по имени
     *
     * @param name - название реалма (поле name)
     * @return реалм (или пустой Optional)
     */
    @Override
    public Optional<RealmDto> getByName(@NonNull final String name) {
        return Optional.ofNullable(realmMapper.realmToRealmDto(realmRepository.findByName(name)
                .orElse(null)));
    }
}
