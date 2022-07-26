package space.eliseev.keycloakadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.eliseev.keycloakadmin.entity.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository <Client, String> {
    Optional<Client> findClientByName(String name);
}
