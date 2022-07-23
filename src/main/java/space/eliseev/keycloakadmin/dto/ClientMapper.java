package space.eliseev.keycloakadmin.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import space.eliseev.keycloakadmin.entity.Client;

@Mapper
public interface ClientMapper {
    @Mapping(target = "realmName", source = "realmId")
    ClientDto clientToClientDto(Client client);
}
