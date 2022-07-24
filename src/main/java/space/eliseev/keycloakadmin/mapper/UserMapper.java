package space.eliseev.keycloakadmin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.entity.Realm;
import space.eliseev.keycloakadmin.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "realmName", source = "realm.realmId")
    UserDto userToUserDto(User user, Realm realm);
}
