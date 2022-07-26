package space.eliseev.keycloakadmin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.eliseev.keycloakadmin.entity.Event;
import space.eliseev.keycloakadmin.service.EventService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value ="/event", produces = "application/json; charset=UTF-8")
public class EventController {
    private final EventService eventService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Event>> getEvents() {
        return new ResponseEntity<>(eventService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        final Optional<Event> event = eventService.getById(id);

        return event
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{user}/all")
    public ResponseEntity<List<Event>> getUserEvents(@PathVariable("user") String username) {
        return new ResponseEntity<>(eventService.getAllByUsername(username), HttpStatus.OK);
    }

    @GetMapping(value = "/all/{time}")
    public ResponseEntity<List<Event>> getAllByTime(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return new ResponseEntity<>(eventService.getByDateCreatedBetween(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping(value = "/user/all/time")
    public ResponseEntity<List<Event>> getEventsByUserInTimePeriod(@RequestParam String username,
                                                                   @RequestParam LocalDateTime startDate,
                                                                   @RequestParam LocalDateTime endDate) {
        return new ResponseEntity<>(eventService.getByUsernameAndDateCreatedBetween(username, startDate, endDate), HttpStatus.OK);
    }
}
