package space.eliseev.keycloakadmin.service;

import space.eliseev.keycloakadmin.dto.ClientDto;

import java.util.List;

public interface ClientFormBuilder {
    byte[] dowload(List<ClientDto> list);
}
