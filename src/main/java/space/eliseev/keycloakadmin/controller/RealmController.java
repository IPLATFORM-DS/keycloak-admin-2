package space.eliseev.keycloakadmin.controller;

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

@RestController
@RequiredArgsConstructor
@RequestMapping(value="realm", produces = "application/json; charset=UTF-8")
public class RealmController {

    private final RealmService realmService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Realm>> getAllRealmList() {
        return new ResponseEntity<>(realmService.getAllRealms(), HttpStatus.OK);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<Realm> getRoleById(@PathVariable(name = "id") String id) {
        final Optional<Realm> realm = realmService.getById(id);
        return realm
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Realm(), HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/getByName/{name}")
    public ResponseEntity<Optional<Realm>> getRealmByName(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(realmService.getByName(name), HttpStatus.OK);
    }
}
