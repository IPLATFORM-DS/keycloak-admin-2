package space.eliseev.keycloakadmin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import space.eliseev.keycloakadmin.dto.RoleDto;
import space.eliseev.keycloakadmin.entity.Role;
import space.eliseev.keycloakadmin.service.RoleService;

import java.util.List;
import java.util.Optional;

/**
 * Получение информации о ролях
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/role", produces = "application/json; charset=UTF-8")
@Tag(name = "role", description = "The Role API")
public class RoleController {
    private final RoleService roleService;

    @Operation(summary = "Get all roles", description = "It can be used to get list of all roles in all realms",
            tags = {"role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Role.class))),
                    description = "successful operation (may be empty list)")
    })
    @GetMapping(value = "/getAll", produces = {"application/json"})
    public ResponseEntity<List<RoleDto>> getAllRolesList() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @Operation(summary = "Get role by ID", description = "It returns one role with specified id", tags = {"role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "404", description = "role not found", content = @Content)
    })
    @GetMapping(value = "/getById/{id}", produces = {"application/json"})
    public ResponseEntity<RoleDto> getRoleById(@Parameter(required = true, description = "ID of requested role")
                                               @PathVariable(name = "id") String id) {
        final Optional<RoleDto> role = roleService.getById(id);
        return role
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get role by name", description = "It returns list of roles with specified name in all realms",
            tags = {"role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Role.class))),
                    description = "successful operation (may be empty list)")
    })
    @GetMapping(value = "/getByName/{name}", produces = {"application/json"})
    public ResponseEntity<List<RoleDto>> getRoleByName(@Parameter(required = true,
            description = "Name of requested role (or roles, if they exists in different realms)")
                                                       @PathVariable(name = "name") String name) {
        return new ResponseEntity<>(roleService.getByName(name), HttpStatus.OK);
    }
}
