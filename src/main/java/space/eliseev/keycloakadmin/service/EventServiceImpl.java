package space.eliseev.keycloakadmin.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.commons.TimeUtils;
import space.eliseev.keycloakadmin.dto.EventDto;
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
                .map(event -> {
                    LocalDateTime time = TimeUtils.toLocalDateTime(event.getEventTime());
                    EventDto dto = eventMapper.eventToEventDtO(event);
                    dto.setEventTime(time);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EventDto> getById(@NonNull final String id) {
        Optional<Event> event = eventRepository.findById(id);
        EventDto dto = null;
        if (event.isPresent()) {
            LocalDateTime time = TimeUtils.toLocalDateTime(event.get().getEventTime());
            dto = eventMapper.eventToEventDtO(event.orElse(null));
            dto.setEventTime(time);
        }
        return Optional.ofNullable(dto);
    }

    @Override
    public List<EventDto> getAllByUsername(@NonNull String userId) {
        return eventRepository
                .findAllByUsername(userId)
                .stream()
                .map(event -> {
                    LocalDateTime time = TimeUtils.toLocalDateTime(event.getEventTime());
                    EventDto dto = eventMapper.eventToEventDtO(event);
                    dto.setEventTime(time);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getByDateCreatedBetween(@NonNull LocalDateTime startDate,
                                                  @NonNull LocalDateTime endDate) {
        return eventRepository
                .findByDateCreatedBetween(TimeUtils.toLong(startDate), TimeUtils.toLong(endDate))
                .stream()
                .map(event -> {
                    LocalDateTime time = TimeUtils.toLocalDateTime(event.getEventTime());
                    EventDto dto = eventMapper.eventToEventDtO(event);
                    dto.setEventTime(time);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getByUsernameAndDateCreatedBetween(@NonNull String userId,
                                                             @NonNull LocalDateTime startDate,
                                                             @NonNull LocalDateTime endDate) {
        return eventRepository
                .findByUsernameAndDateCreatedBetween(userId, TimeUtils.toLong(startDate), TimeUtils.toLong(endDate))
                .stream()
                .map(event -> {
                    LocalDateTime time = TimeUtils.toLocalDateTime(event.getEventTime());
                    EventDto dto = eventMapper.eventToEventDtO(event);
                    dto.setEventTime(time);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
