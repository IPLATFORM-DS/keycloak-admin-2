package space.eliseev.keycloakadmin.service;

import space.eliseev.keycloakadmin.dto.UserDto;

import java.util.List;

public interface UserFormBuilder {
    byte[] download(List<UserDto> list);
}
