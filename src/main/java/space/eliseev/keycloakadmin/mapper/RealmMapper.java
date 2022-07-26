package space.eliseev.keycloakadmin.mapper;

import org.mapstruct.Mapper;
import space.eliseev.keycloakadmin.dto.RealmDto;
import space.eliseev.keycloakadmin.entity.Realm;

@Mapper(componentModel = "spting")
public interface RealmMapper {

    RealmDto realmToRealmDto(Realm realm);

}
