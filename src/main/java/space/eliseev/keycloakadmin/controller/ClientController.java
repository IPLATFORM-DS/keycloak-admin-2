package space.eliseev.keycloakadmin.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.eliseev.keycloakadmin.dto.ClientDto;
import space.eliseev.keycloakadmin.service.ClientServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/client", produces = "application/json; charset=UTF-8")
public class ClientController {

    private final ClientServiceImpl clientService;

    @GetMapping(value = "/getAll")
    ResponseEntity<List<ClientDto>> getClients() {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    ResponseEntity<ClientDto> getClientById(@PathVariable("id") String id) {
        final Optional<ClientDto> client = clientService.getById(id);
        return client
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping(value = "/getByName/{name}")
    ResponseEntity<ClientDto> getClientByName(@PathVariable("name") String name) {
        final Optional<ClientDto> client = clientService.getClientByName(name);
        return client
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
