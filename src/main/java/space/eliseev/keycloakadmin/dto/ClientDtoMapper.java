package space.eliseev.keycloakadmin.dto;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import space.eliseev.keycloakadmin.entity.Client;

@Component
public class ClientDtoMapper {
    public ClientDto mapToClientDto(@NonNull Client client) {

        ClientDto clientDto = new ClientDto();

        clientDto.setEnabled(client.getEnabled());

        clientDto.setClientName(client.getClientId());

        clientDto.setPublicClient(client.getPublicClient());

        clientDto.setSecret(client.getSecret());

        clientDto.setRealmName(client.getRealmId());

        clientDto.setProtocol(client.getProtocol());

        clientDto.setName(client.getName());

        clientDto.setClientAuthenticatorType(client.getClientAuthenticatorType());

        clientDto.setDescription(client.getDescription());

        return clientDto;
    }
}
