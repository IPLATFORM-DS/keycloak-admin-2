package space.eliseev.keycloakadmin.commons;

import lombok.RequiredArgsConstructor;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.entity.User;
import space.eliseev.keycloakadmin.service.UserFormBuilder;
import space.eliseev.keycloakadmin.service.UserFormBuilderCsv;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class UserFormBuilderFactory {
    private final UserFormBuilderCsv userFormBuilderCsv;
    private final Map<String, Supplier<UserFormBuilder>> map;

    public byte[] download(List<UserDto> data, String fileType) {
        return map.get(fileType).get().download(data);
    }
}
