package space.eliseev.keycloakadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.eliseev.keycloakadmin.entity.Realm;

import java.util.Optional;

@Repository
public interface RealmRepository extends JpaRepository<Realm, String> {
    Optional<Realm> findByName(String name);

}
