package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.ClientDto;
import space.eliseev.keycloakadmin.dto.ClientDtoMapper;
import space.eliseev.keycloakadmin.entity.Client;
import space.eliseev.keycloakadmin.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientDtoMapper clientDtoMapper;

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

    public List<ClientDto> getAllClientsDto() {
        return getAllClients().stream().map(clientDtoMapper :: mapToClientDto)
                .collect(Collectors.toList());
    }

    public Optional<ClientDto> getClientDtoById(@NonNull final String id) {
        Client client = getById(id).orElse(new Client());
        return Optional.ofNullable(clientDtoMapper.mapToClientDto(client));
    }

    public Optional<ClientDto> getClientDtoByName(@NonNull final String name) {
        Client client = getClientByName(name).orElse(new Client());
        return Optional.ofNullable(clientDtoMapper.mapToClientDto(client));
    }

}
