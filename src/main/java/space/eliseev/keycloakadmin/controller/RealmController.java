package space.eliseev.keycloakadmin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.eliseev.keycloakadmin.entity.Realm;
import space.eliseev.keycloakadmin.service.RealmService;

import java.util.List;
import java.util.Optional;

/**
 * Получение информации о реалмах
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "realm", produces = "application/json; charset=UTF-8")
@Tag(name = "realm", description = "The Realm API")
public class RealmController {

    private final RealmService realmService;

    @Operation(summary = "Get all realms", description = "It can be used to get list of all realms",
            tags = {"realm"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Realm.class))),
                    description = "successful operation (may be empty list)")
    })
    @GetMapping(value = "/getAll", produces = {"application/json"})
    public ResponseEntity<List<Realm>> getAllRealmList() {
        return new ResponseEntity<>(realmService.getAllRealms(), HttpStatus.OK);
    }

    @Operation(summary = "Get realm by ID", description = "It returns one realm with specified id", tags = {"realm"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Realm.class))),
            @ApiResponse(responseCode = "404", description = "realm not found", content = @Content)
    })
    @GetMapping(value = "/getById/{id}", produces = {"application/json"})
    public ResponseEntity<Realm> getRealmById(@PathVariable(name = "id") String id) {
        final Optional<Realm> realm = realmService.getById(id);
        return realm
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Realm(), HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get realm by name", description = "It returns one realm with specified name", tags = {"realm"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Realm.class))),
            @ApiResponse(responseCode = "404", description = "realm not found", content = @Content)
    })
    @GetMapping(value = "/getByName/{name}", produces = {"application/json"})
    public ResponseEntity<Realm> getRealmByName(@PathVariable(name = "name") String name) {
        final Optional<Realm> realm = realmService.getByName(name);
        return realm
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Realm(), HttpStatus.NOT_FOUND));
    }
}
