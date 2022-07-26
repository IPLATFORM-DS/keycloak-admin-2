package space.eliseev.keycloakadmin.commons;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import space.eliseev.keycloakadmin.entity.User;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;
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
        FileType type;
        try {
            type = FileType.valueOf(fileType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadFileFormatExeption("Ошибка в процессе определения формата. Формат не найден(2)");
        }
        UserFormBuilder builder = map.get(type);
        if (builder == null) {
            throw new BadFileFormatExeption("Ошибка в процессе обработки формата");
        }
        return builder.download(data);
    }

    @Bean
    public void setMap() {
        if (map.size() == 0) {
            map.put(FileType.CSV, userFormBuilderCsv);
            map.put(FileType.XLSX, userFormBuilderXlsx);
        }
    }

    private enum FileType {
        XLSX,
        CSV
    }
}
