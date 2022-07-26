package space.eliseev.keycloakadmin.service;

import space.eliseev.keycloakadmin.entity.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> getAll();
    Optional<Event> getById(String id);
    List<Event> getAllByUsername(String username);
    List<Event> getByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Event> getByUsernameAndDateCreatedBetween(String username, LocalDateTime startDate, LocalDateTime endDate);
}
