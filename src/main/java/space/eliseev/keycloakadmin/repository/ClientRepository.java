package space.eliseev.keycloakadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import space.eliseev.keycloakadmin.entity.Client;

import java.util.List;
@Repository
public interface ClientRepository extends JpaRepository <Client, String> {
    List<Client> findClientByName(String name);
}