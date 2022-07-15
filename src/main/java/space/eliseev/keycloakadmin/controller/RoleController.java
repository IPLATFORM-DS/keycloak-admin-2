package space.eliseev.keycloakadmin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import space.eliseev.keycloakadmin.entity.Role;
import space.eliseev.keycloakadmin.service.RoleService;

import java.util.List;
import java.util.Optional;
/**
 * Получение информации о ролях
 * */
@RestController
@RequiredArgsConstructor
@RequestMapping(value ="/role", produces = "application/json; charset=UTF-8")
public class RoleController {

    private final RoleService roleService;

    @GetMapping(value = "/all")
    List<Role> getAllRolesList() {
        return roleService.findAll();
    }

    @GetMapping(value = "/byId/{id}")
    Role getRoleById(@PathVariable(name = "id") String id) {
        Optional<Role> role = roleService.findById(id);
        return role.orElse(new Role());
    }

    @GetMapping(value = "/byName/{name}")
    List<Role> getRoleByName(@PathVariable(name = "name") String name) {
        return roleService.findByName(name);
    }
}
