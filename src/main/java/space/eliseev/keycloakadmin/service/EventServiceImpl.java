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
    private final UserService userService;
    private final RealmService realmService;
    private final ClientService clientService;

    @Override
    public List<EventDto> getAll() {
        return eventRepository
                .findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EventDto> getById(@NonNull final String id) {
        Optional<Event> event = eventRepository.findById(id);
        EventDto toDto = event.map(this::toDto).orElse(null);
        return Optional.ofNullable(toDto);
    }

    @Override
    public List<EventDto> getByDateCreatedBetween(@NonNull LocalDateTime startDate,
                                                  @NonNull LocalDateTime endDate) {
        return eventRepository
                .findByDateCreatedBetween(TimeUtils.toLong(startDate), TimeUtils.toLong(endDate))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getByUserIdAndDateCreatedBetween(@NonNull String username,
                                                             @NonNull LocalDateTime startDate,
                                                             @NonNull LocalDateTime endDate) {
        return eventRepository
                .findByUserIdeAndDateCreatedBetween(username, TimeUtils.toLong(startDate), TimeUtils.toLong(endDate))
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> findAllByUsername(String username) {
        return eventRepository.findAllByUsername(username)
                .stream()
                .map(eventMapper::eventToEventDtO)
                .collect(Collectors.toList());
    }

    private EventDto toDto(Event event) {
        Optional<RealmDto> realm = realmService.getById(event.getRealmId());
        String realmName = realm.map(RealmDto::getName).orElse(null);

        Optional<UserDto> user = userService.getById(event.getUserId());
        String userName = user.map(UserDto::getUsername).orElse(null);

        Optional<ClientDto> client = clientService.getById(event.getClientId());
        String clientName = client.map(ClientDto::getName).orElse(null);

        LocalDateTime time = TimeUtils.toLocalDateTime(event.getEventTime());
        EventDto dto = eventMapper.eventToEventDtO(event);

        dto.setRealmName(realmName);
        dto.setUserName(userName);
        dto.setClientName(clientName);
        dto.setEventTime(time);
        return dto;
    }
}
