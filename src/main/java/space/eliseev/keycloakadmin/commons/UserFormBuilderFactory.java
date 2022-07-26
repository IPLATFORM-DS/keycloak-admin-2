package space.eliseev.keycloakadmin.commons;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import space.eliseev.keycloakadmin.entity.User;
import space.eliseev.keycloakadmin.service.UserFormBuilder;
import space.eliseev.keycloakadmin.service.UserFormBuilderCsv;
import space.eliseev.keycloakadmin.service.UserFormBuilderXlsx;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserFormBuilderFactory {
    private final UserFormBuilderCsv userFormBuilderCsv;
    private final UserFormBuilderXlsx userFormBuilderXlsx;
    private final Map<FileType, UserFormBuilder> map;

    public byte[] download(List<User> data, String fileType) {

        if (map.size() == 0) {
            map.put(FileType.csv, userFormBuilderCsv);
            map.put(FileType.xlsx, userFormBuilderXlsx);
        }
        return map.get(FileType.valueOf(fileType)).download(data);
    }
    private enum FileType {
        xlsx,
        csv
    }
}
