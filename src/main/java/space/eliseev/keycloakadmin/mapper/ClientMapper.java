package space.eliseev.keycloakadmin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import space.eliseev.keycloakadmin.dto.ClientDto;
import space.eliseev.keycloakadmin.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "realmName", source = "realmId")
    ClientDto clientToClientDto(Client client);
}
