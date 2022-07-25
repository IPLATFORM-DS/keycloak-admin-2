package space.eliseev.keycloakadmin.service;

import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.entity.User;

import java.util.List;

public interface UserFormBuilder {
    byte[] download(List<User> list);
}
