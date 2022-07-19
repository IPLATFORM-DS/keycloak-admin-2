package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import space.eliseev.keycloakadmin.entity.Realm;


import java.util.List;
import java.util.Optional;

public interface RealmService {
    List<Realm> getAllRealms();

    Optional<Realm> getById(@NonNull String id);

    Optional<Realm> getByName(@NonNull String name);
}
