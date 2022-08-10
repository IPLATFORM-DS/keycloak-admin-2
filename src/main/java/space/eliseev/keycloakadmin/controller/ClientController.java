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
import space.eliseev.keycloakadmin.commons.ClientFormBuilderFactory;
import space.eliseev.keycloakadmin.dto.ClientDto;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;
import space.eliseev.keycloakadmin.service.ClientServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/client", produces = "application/json; charset=UTF-8")
@Tag(name = "client", description = "The Client API")
public class ClientController {

    private final ClientServiceImpl clientService;
    private final ClientFormBuilderFactory clientFormBuilderFactory;
    @Operation(summary = "Get all clients", description = "It can be used to get list of all clients in all realms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientDto.class))),
                    description = "Successful operation (List may be empty)")
    })
    @GetMapping(value = "/getAll")
    ResponseEntity<List<ClientDto>> getClients() {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @Operation(summary = "Get client by ID", description = "It returns one client with specified id",
            tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClientDto.class)),
                    description = "Successful operation"),
            @ApiResponse(responseCode = "404", content = @Content, description = "User not found")
    })
    @GetMapping(value = "/get/{id}")
    ResponseEntity<ClientDto> getClientById(@PathVariable("id") String id) {
        final Optional<ClientDto> client = clientService.getById(id);
        return client
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Get client by name", description = "It returns one client with specified name",
            tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ClientDto.class))),
                    description = "Successful operation (List may be empty)")
    })
    @GetMapping(value = "/getByName/{name}")
    ResponseEntity<List<ClientDto>> getClientByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(clientService.getClientByName(name), HttpStatus.OK);
    }
    @Operation(summary = "Get client list as file", description = "It list of clients in file",
            tags = {"client"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                    schema = @Schema(implementation = ClientDto.class)),
                    description = "Successful operation"),
            @ApiResponse(responseCode = "404", content = @Content, description = "Format not found")
    })
    @GetMapping(value = "/save/{format}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    ResponseEntity<byte[]> saveInCsvOrXlsx(@PathVariable String format) {
        HttpHeaders headers = new HttpHeaders();
        switch (format.toLowerCase()) {
            case "xlsx":
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clientlist.xlsx");
                break;
            case "csv":
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clientlist.csv");
                break;
            default:
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=clientlist");
        }
        return new ResponseEntity<>(clientFormBuilderFactory.download(format.toLowerCase(), clientService.getAllClients()),
                headers, HttpStatus.OK);
    }

    @ExceptionHandler({BadFileFormatExeption.class, IllegalArgumentException.class})
    public ResponseEntity<Object> getBadFileFormatExeption(Exception e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
