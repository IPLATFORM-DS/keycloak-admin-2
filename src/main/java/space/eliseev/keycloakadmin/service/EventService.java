package space.eliseev.keycloakadmin.service;

import space.eliseev.keycloakadmin.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> getAll();
    Optional<Event> getById(String id);
    List<Event> getAllByUsername(String username);
    List<Event> getAllByTime(Long time);
    List<Event> getAllByUsernameAndTime(String username, Long time);
}
