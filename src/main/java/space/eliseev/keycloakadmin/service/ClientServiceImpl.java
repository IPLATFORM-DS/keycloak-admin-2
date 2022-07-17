package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.entity.Client;
import space.eliseev.keycloakadmin.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getById(@NonNull final String id) {
        return clientRepository.findById(id);
    }

    @Override
    public Optional<Client> getClientByName(@NonNull final String name) {
        return clientRepository.findClientByName(name);
    }


}
