package space.eliseev.keycloakadmin.service;

import space.eliseev.keycloakadmin.dto.EventDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventService {
    List<EventDto> getAll();

    Optional<EventDto> getById(String id);

    List<EventDto> getByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<EventDto> getByUserIdAndDateCreatedBetween(String username, LocalDateTime startDate, LocalDateTime endDate);

    List<EventDto> findByUsername(String username);
}
