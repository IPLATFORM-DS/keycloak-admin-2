package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.commons.TimeUtils;
import space.eliseev.keycloakadmin.dto.RealmDto;
import space.eliseev.keycloakadmin.entity.Realm;
import space.eliseev.keycloakadmin.mapper.RealmMapper;
import space.eliseev.keycloakadmin.repository.RealmRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RealmServiceImpl implements RealmService {

    private final RealmRepository realmRepository;
    private final RealmMapper realmMapper;

    @Override
    public List<RealmDto> getAllRealms() {
        return realmRepository.findAll()
                .stream()
                .map(realm -> {
                    LocalDateTime time = TimeUtils.toLocalDateTime(realm.getEventsExpiration());
                    RealmDto dto = realmMapper.realmToRealmDto(realm);
                    dto.setEventsExpirationLocalDateTime(time);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RealmDto> getById(@NonNull final String id) {
        Optional<Realm> realm = realmRepository.findById(id);
        RealmDto toDto = null;
        if (realm.isPresent()) {
            LocalDateTime time = TimeUtils.toLocalDateTime(realm.get().getEventsExpiration());
            toDto = realmMapper.realmToRealmDto(realm.orElse(null));
            toDto.setEventsExpirationLocalDateTime(time);
        }
        return Optional.ofNullable(toDto);
    }

    @Override
    public Optional<RealmDto> getByName(@NonNull final String name) {
        Optional<Realm> realm = realmRepository.findByName(name);
        RealmDto toDto = null;
        if (realm.isPresent()) {
            LocalDateTime time = TimeUtils.toLocalDateTime(realm.get().getEventsExpiration());
            toDto = realmMapper.realmToRealmDto(realm.orElse(null));
            toDto.setEventsExpirationLocalDateTime(time);
        }
        return Optional.ofNullable(toDto);
    }
}
