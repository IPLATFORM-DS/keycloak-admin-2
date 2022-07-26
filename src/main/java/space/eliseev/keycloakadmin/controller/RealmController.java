package space.eliseev.keycloakadmin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.eliseev.keycloakadmin.dto.RealmDto;
import space.eliseev.keycloakadmin.service.RealmService;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="realm", produces = "application/json; charset=UTF-8")
public class RealmController {

    private final RealmService realmService;

    @Operation(summary = "Get all realms", description = "It can be used to get list of all realms",
            tags = {"realm"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = RealmDto.class))),
                    description = "successful operation (may be empty list)")
    })
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<RealmDto>> getAllRealmList() {
        return new ResponseEntity<>(realmService.getAllRealms(), HttpStatus.OK);
    }

    @Operation(summary = "Get realm by ID", description = "It returns one realm with specified id", tags = {"realm"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RealmDto.class))),
            @ApiResponse(responseCode = "404", description = "realm not found", content = @Content)
    })
    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<RealmDto> getRoleById(@PathVariable(name = "id") String id) {
        final Optional<RealmDto> realm = realmService.getById(id);
        return realm
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get realm by name", description = "It returns one realm with specified name", tags = {"realm"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RealmDto.class))),
            @ApiResponse(responseCode = "404", description = "realm not found", content = @Content)
    })
    @GetMapping(value = "/getByName/{name}")
    public ResponseEntity<RealmDto> getRealmByName(@PathVariable(name = "name") String name) {
        final Optional<RealmDto> realm = realmService.getByName(name);
        return realm
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
