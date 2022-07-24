package space.eliseev.keycloakadmin.mapper;

import org.mapstruct.Mapper;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);
}
