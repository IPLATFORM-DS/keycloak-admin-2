package space.eliseev.keycloakadmin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import space.eliseev.keycloakadmin.commons.TimeUtils;
import space.eliseev.keycloakadmin.dto.EventDto;
import space.eliseev.keycloakadmin.entity.Event;


@Mapper(componentModel = "spring", uses = TimeUtils.class)
public interface EventMapper {
    @Mapping(target = "clientName", source = "clientId")
    @Mapping(target = "realmName", source = "realmId")
    @Mapping(target = "userName", source = "userId")
    EventDto eventToEventDtO(Event event);
}
