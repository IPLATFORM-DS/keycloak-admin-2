package space.eliseev.keycloakadmin.commons;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import space.eliseev.keycloakadmin.dto.RoleDto;
import space.eliseev.keycloakadmin.service.RoleFormBuilder;
import space.eliseev.keycloakadmin.service.RoleFormBuilderCsv;
import space.eliseev.keycloakadmin.service.RoleFormBuilderXlsx;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleFormBuilderFactory {
    private final RoleFormBuilderCsv roleFormBuilderCsv;
    private final RoleFormBuilderXlsx roleFormBuilderXlsx;
    private final Map<FileType, Supplier<RoleFormBuilder>> map = new EnumMap<>(FileType.class);

    public byte[] download(List<RoleDto> data, String fileType) {
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
        Optional<RoleFormBuilder> builder = Optional.ofNullable(map.get(type).get());
        if (builder.isEmpty()) {
            log.error("Error during defining format(1). Format not found");
            throw new IllegalArgumentException("Error during defining format(1)");
        }
        return builder.get().download(data);
    }

    @PostConstruct
    public void init() {
        map.put(FileType.CSV, () -> roleFormBuilderCsv);
        map.put(FileType.XLSX, () -> roleFormBuilderXlsx);
    }

    private enum FileType {
        XLSX,
        CSV
    }
}
