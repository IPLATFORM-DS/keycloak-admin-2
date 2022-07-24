package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.ClientDto;
import space.eliseev.keycloakadmin.mapper.ClientMapper;
import space.eliseev.keycloakadmin.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository
                .findAll()
                .stream()
                .map(clientMapper :: clientToClientDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClientDto> getById(@NonNull final String id) {
        return Optional.ofNullable(clientMapper.clientToClientDto(clientRepository.findById(id)
                .orElse(null)));
    }

    @Override
    public Optional<ClientDto> getClientByName(@NonNull final String name) {
        return Optional.ofNullable(clientMapper.clientToClientDto(clientRepository.findClientByName(name)
                .orElse(null)));
    }


}
