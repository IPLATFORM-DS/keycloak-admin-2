package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import space.eliseev.keycloakadmin.dto.ClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<ClientDto> getAllClients();

    Optional<ClientDto> getById(@NonNull String id);

    Optional<ClientDto> getClientByName(@NonNull String name);

}
