package space.eliseev.keycloakadmin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.eliseev.keycloakadmin.entity.Event;
import space.eliseev.keycloakadmin.service.EventService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value ="/event", produces = "application/json; charset=UTF-8")
public class EventController {
    private final EventService eventService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Event>> getEvents() {
        final List<Event> events = eventService.getAll();

        if (events == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(eventService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final Optional<Event> event = eventService.getById(id);

        return event
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/{user}/all")
    public ResponseEntity<List<Event>> getUserEvents(@PathVariable("user") String username) {
        if (username == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final List<Event> events = eventService.getAllByUsername(username);

        if (events == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping(value = "/all/{time}")
    public ResponseEntity<List<Event>> getAllByTime(@PathVariable Long time) {
        if (time == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final List<Event> events = eventService.getAllByTime(time);

        if (events == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping(value = "/{user}/all/{time}")
    public ResponseEntity<List<Event>> getEventsByUserInTimePeriod(@PathVariable("user") String username, @PathVariable Long time) {
        if (username == null || time == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final List<Event> events = eventService.getAllByUsernameAndTime(username, time);

        if (events == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}
