package space.eliseev.keycloakadmin.commons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.entity.User;
import space.eliseev.keycloakadmin.service.UserFormBuilder;
import space.eliseev.keycloakadmin.service.UserFormBuilderCsv;
import space.eliseev.keycloakadmin.service.UserFormBuilderXls;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class UserFormBuilderFactory {
    private final UserFormBuilderCsv userFormBuilderCsv;
    private final UserFormBuilderXls userFormBuilderXls;
    private final Map<Integer, UserFormBuilder> map;

    public byte[] download(List<User> data, Integer fileType) {
        System.out.println(fileType);

        if (map.size() == 0) {
            map.put(1, userFormBuilderCsv);
            map.put(2, userFormBuilderXls);
        }
        return map.get(fileType).download(data);
    }
}
