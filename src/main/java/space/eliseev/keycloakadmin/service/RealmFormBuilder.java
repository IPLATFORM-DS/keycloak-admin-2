package space.eliseev.keycloakadmin.service;

import space.eliseev.keycloakadmin.dto.RealmDto;

import java.util.List;

public interface RealmFormBuilder {
    byte[] download(List<RealmDto> list);
}
