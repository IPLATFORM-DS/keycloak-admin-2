package space.eliseev.keycloakadmin.service;

import space.eliseev.keycloakadmin.dto.EventDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventService {
    /**
     * Получить список всех пользователей
     * @return список событий
     */
    List<EventDto> getAll();

    /**
     * Получить событие по идентификатору
     * @param id идентификатор события
     * @return событие
     */
    Optional<EventDto> getById(String id);

    /**
     * Получить список событий в определенном промежутке времени
     * @param startDate дата начала
     * @param endDate дата конца
     * @return список событий
     */
    List<EventDto> getByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Получить все события определенного пользователя в определенном промежутке времени
     * @param username пользователя
     * @param startDate дата начала
     * @param endDate дата конца
     * @return список событий
     */
    List<EventDto> getByUsernameAndDateCreatedBetween(String username, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Получить все события определенного пользователя
     * @param username пользователя
     * @return список событий
     */
    List<EventDto> getAllByUsername(String username);
}
