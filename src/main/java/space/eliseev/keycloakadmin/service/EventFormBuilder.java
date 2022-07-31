package space.eliseev.keycloakadmin.service;

import space.eliseev.keycloakadmin.dto.EventDto;

import java.util.List;

public interface EventFormBuilder {
    byte[] download(List<EventDto> list);
}
