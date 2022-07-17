package space.eliseev.keycloakadmin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.entity.Event;
import space.eliseev.keycloakadmin.repository.EventRepository;

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
    public Optional<Event> getById(String id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> getAllByUsername(String username) {
        return eventRepository.findAllByUsername(username);
    }

    @Override
    public List<Event> getAllByTime(Long time) {
        return eventRepository.findAllByTime(time);
    }

    @Override
    public List<Event> getAllByUsernameAndTime(String username, Long time) {
        return eventRepository.findAllByUsernameAndTime(username, time);
    }
}
