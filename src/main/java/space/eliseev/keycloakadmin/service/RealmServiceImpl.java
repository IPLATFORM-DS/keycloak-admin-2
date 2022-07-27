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

    @Override
    public List<RealmDto> getAllRealms() {
        return realmRepository.findAll()
                .stream()
                .map(realmMapper::realmToRealmDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RealmDto> getById(@NonNull final String id) {
        return Optional.ofNullable(realmMapper.realmToRealmDto(realmRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public Optional<RealmDto> getByName(@NonNull final String name) {
        return Optional.ofNullable(realmMapper.realmToRealmDto(realmRepository.findByName(name)
                .orElse(null)));
    }
}
