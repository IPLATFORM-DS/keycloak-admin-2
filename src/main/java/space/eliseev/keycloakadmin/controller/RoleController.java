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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.eliseev.keycloakadmin.commons.RoleFormBuilderFactory;
import space.eliseev.keycloakadmin.dto.RoleDto;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;
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
    private final RoleFormBuilderFactory roleFormBuilderFactory;

    @Operation(summary = "Get all roles", description = "It can be used to get list of all roles in all realms",
            tags = {"role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = RoleDto.class))),
                    description = "successful operation (may be empty list)")
    })
    @GetMapping(value = "/getAll", produces = {"application/json"})
    public ResponseEntity<List<RoleDto>> getAllRolesList() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @Operation(summary = "Get role by ID", description = "It returns one role with specified id", tags = {"role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoleDto.class))),
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
                    array = @ArraySchema(schema = @Schema(implementation = RoleDto.class))),
                    description = "successful operation (may be empty list)")
    })
    @GetMapping(value = "/getByName/{name}", produces = {"application/json"})
    public ResponseEntity<List<RoleDto>> getRoleByName(@Parameter(required = true,
            description = "Name of requested role (or roles, if they exists in different realms)")
                                                       @PathVariable(name = "name") String name) {
        return new ResponseEntity<>(roleService.getByName(name), HttpStatus.OK);
    }

    @Operation(summary = "Get role list as file", description = "It list of roles in file",
            tags = {"role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                    schema = @Schema(implementation = RoleDto.class)),
                    description = "Successful operation"),
            @ApiResponse(responseCode = "404", content = @Content, description = "Format not found")
    })
    @GetMapping(value = "/save/{format}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> saveInCsv(@PathVariable String format) {
        HttpHeaders headers = new HttpHeaders();
        switch (format) {
            case "XLSX":
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=rolelist.xlsx");
                break;
            case "CSV":
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=rolelist.csv");
                break;
            default:
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=rolelist");
        }
        return new ResponseEntity<>(roleFormBuilderFactory.download(roleService.getAllRoles(), format),
                headers, HttpStatus.OK);
    }

    @ExceptionHandler({BadFileFormatExeption.class, IllegalArgumentException.class})
    public ResponseEntity<Void> getBadFileFormatExeption(Exception e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
