package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.commons.TimeUtils;
import space.eliseev.keycloakadmin.dto.ClientDto;
import space.eliseev.keycloakadmin.dto.EventDto;
import space.eliseev.keycloakadmin.dto.RealmDto;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.entity.Event;
import space.eliseev.keycloakadmin.mapper.EventMapper;
import space.eliseev.keycloakadmin.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public List<EventDto> getAll() {
        return eventRepository
                .findAll()
                .stream()
                .map(eventMapper::eventToEventDtO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EventDto> getById(@NonNull final String id) {
        return eventRepository.findById(id).map(eventMapper::eventToEventDtO);
    }

    @Override
    public List<EventDto> getAllByUserId(@NonNull String userId) {
        return eventRepository
                .findAllByUserId(userId)
                .stream()
                .map(eventMapper::eventToEventDtO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getByDateCreatedBetween(@NonNull LocalDateTime startDate,
                                                  @NonNull LocalDateTime endDate) {
        return eventRepository
                .findByDateCreatedBetween(TimeUtils.toLong(startDate), TimeUtils.toLong(endDate))
                .stream()
                .map(eventMapper::eventToEventDtO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getByUserIdAndDateCreatedBetween(@NonNull String userId,
                                                             @NonNull LocalDateTime startDate,
                                                             @NonNull LocalDateTime endDate) {
        return eventRepository
                .findByUserIdeAndDateCreatedBetween(userId, TimeUtils.toLong(startDate), TimeUtils.toLong(endDate))
                .stream()
                .map(eventMapper::eventToEventDtO)
                .collect(Collectors.toList());
    }

}
