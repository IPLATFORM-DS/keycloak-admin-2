package space.eliseev.keycloakadmin.commons;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.service.UserFormBuilder;
import space.eliseev.keycloakadmin.service.UserFormBuilderCsv;
import space.eliseev.keycloakadmin.service.UserFormBuilderXlsx;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFormBuilderFactory {
    private final UserFormBuilderCsv userFormBuilderCsv;
    private final UserFormBuilderXlsx userFormBuilderXlsx;
    private final Map<FileType, Supplier<UserFormBuilder>> map = new EnumMap<>(FileType.class);

    public byte[] download(List<UserDto> data, String fileType) {
        FileType type;
        try {
            type = FileType.valueOf(fileType.toUpperCase());
        } catch (IllegalArgumentException e) {
            String error = new StringBuilder().append("Error during defining format(2). Format not found").append("\n")
                            .append(e.getMessage()).append("\n")
                            .append(e.getCause()).append("\n").toString();
            log.error(error);
            throw e;
        }
        Optional<UserFormBuilder> builder = Optional.ofNullable(map.get(type).get());
        if (builder.isEmpty()) {
            log.error("Error during defining format(1). Format not found");
            throw new IllegalArgumentException("Error during defining format(1)");
        }
        return builder.get().download(data);
    }

    @PostConstruct
    public void init() {
        map.put(FileType.CSV, () -> userFormBuilderCsv);
        map.put(FileType.XLSX, () -> userFormBuilderXlsx);
    }

    private enum FileType {
        XLSX,
        CSV
    }
}
