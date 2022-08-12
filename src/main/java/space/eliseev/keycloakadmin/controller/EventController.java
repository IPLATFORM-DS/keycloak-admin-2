package space.eliseev.keycloakadmin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.eliseev.keycloakadmin.commons.EventFormBuilderFactory;
import space.eliseev.keycloakadmin.dto.EventDto;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;
import space.eliseev.keycloakadmin.service.EventService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/event", produces = "application/json; charset=UTF-8")
@Tag(name = "event", description = "The Event API")
public class EventController {
    private final EventService eventService;
    private final EventFormBuilderFactory eventFormBuilderFactory;

    @Operation(summary = "Get all events", description = "It can be used to get the list of all events in all realms",
            tags = {"event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = EventDto.class))),
                    description = "Successful operation")
    })
    @GetMapping(value = "/all")
    public ResponseEntity<List<EventDto>> getEvents() {
        return new ResponseEntity<>(eventService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get event by ID", description = "It returns one event with specified id",
            tags = {"event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = EventDto.class))),
                    description = "Successful operation"),
            @ApiResponse(responseCode = "404", content = @Content, description = "Event not found")
    })
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable String id) {
        final Optional<EventDto> event = eventService.getById(id);

        return event
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get all events for specific user",
            description = "It can be used to get the list of all events for a user in all realms",
            tags = {"event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = EventDto.class))),
                    description = "Successful operation")
    })
    @GetMapping(value = "/user/all")
    public ResponseEntity<List<EventDto>> getUserEvents(@RequestParam String username) {
        return new ResponseEntity<>(eventService.findByUsername(username), HttpStatus.OK);
    }

    @Operation(summary = "Get all events in specific time frames",
            description = "It can be used to get the list of all events in specific time frames in all realms. Input date format: `yyyy-MM-dd HH:mm` like `2022-02-08T12:41:29`",
            tags = {"event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = EventDto.class))),
                    description = "Successful operation")
    })
    @GetMapping(value = "/all/time")
    public ResponseEntity<List<EventDto>> getAllByTime(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return new ResponseEntity<>(eventService.getByDateCreatedBetween(startDate, endDate), HttpStatus.OK);
    }

    @Operation(summary = "Get all events in specific time frames for a specific user",
            description = "It can be used to get the list of all events in specific time frames for a specific user in all realms. Input date format: `yyyy-MM-dd HH:mm` like `2022-02-08T12:41:29`",
            tags = {"event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = EventDto.class))),
                    description = "Successful operation")
    })
    @GetMapping(value = "/user/all/time")
    public ResponseEntity<List<EventDto>> getEventsByUserInTimePeriod(@RequestParam String username,
                                                                      @RequestParam LocalDateTime startDate,
                                                                      @RequestParam LocalDateTime endDate) {
        return new ResponseEntity<>(eventService.getByUserIdAndDateCreatedBetween(username, startDate, endDate), HttpStatus.OK);
    }

    @Operation(summary = "Get event list as file", description = "The list of events in file",
            tags = {"event"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                    schema = @Schema(implementation = EventDto.class)),
                    description = "Successful operation"),
            @ApiResponse(responseCode = "404", content = @Content, description = "Format not found")
    })
    @GetMapping(value = "/save/{format}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> saveInCsv(@PathVariable String format) {
        HttpHeaders headers = new HttpHeaders();
        switch (format) {
            case "XLSX":
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=events.xlsx");
                break;
            case "CSV":
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=events.csv");
                break;
            default:
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=events");
        }
        return new ResponseEntity<>(eventFormBuilderFactory.download(eventService.getAll(), format),
                headers, HttpStatus.OK);
    }

    @ExceptionHandler({BadFileFormatExeption.class, IllegalArgumentException.class})
    public ResponseEntity<Exception> getBadFileFormatException(Exception e) {
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }
}
