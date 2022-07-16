package space.eliseev.keycloakadmin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
public class RoleController {

    private final RoleService roleService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Role>> getAllRolesList() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(name = "id") String id) {
        Optional<Role> role = roleService.getById(id);
        return role
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Role(), HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/getByName/{name}")
    public ResponseEntity<List<Role>> getRoleByName(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(roleService.getByName(name), HttpStatus.OK);
    }
}
