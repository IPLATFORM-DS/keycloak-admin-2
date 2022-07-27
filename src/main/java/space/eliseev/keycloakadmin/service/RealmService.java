package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import space.eliseev.keycloakadmin.dto.RealmDto;


import java.util.List;
import java.util.Optional;

public interface RealmService {
    List<RealmDto> getAllRealms();

    Optional<RealmDto> getById(@NonNull String id);

    Optional<RealmDto> getByName(@NonNull String name);
}
