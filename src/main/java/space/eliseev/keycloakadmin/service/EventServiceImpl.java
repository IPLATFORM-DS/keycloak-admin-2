package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.entity.Event;
import space.eliseev.keycloakadmin.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> getById(@NonNull String id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> getAllByUsername(@NonNull String username) {
        return eventRepository.findAllByUsername(username);
    }

    @Override
    public List<Event> getByDateCreatedBetween(@NonNull LocalDateTime startDate, @NonNull LocalDateTime endDate) {
        return eventRepository.findByDateCreatedBetween(startDate, endDate);
    }

    @Override
    public List<Event> getByUsernameAndDateCreatedBetween(@NonNull String username, @NonNull LocalDateTime startDate, @NonNull LocalDateTime endDate) {
        return eventRepository.findByUsernameAndDateCreatedBetween(username, startDate, endDate);
    }
}
