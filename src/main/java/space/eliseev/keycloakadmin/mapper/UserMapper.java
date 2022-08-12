package space.eliseev.keycloakadmin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import space.eliseev.keycloakadmin.commons.TimeUtils;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.entity.User;

@Mapper(componentModel = "spring", uses = TimeUtils.class)
public interface UserMapper {
    @Mapping(target = "realmName", source = "realm.name")
    @Mapping(target = "createdTimestampLocalDateTime", source = "createdTimestamp")
    UserDto userToUserDto(User user);
}
