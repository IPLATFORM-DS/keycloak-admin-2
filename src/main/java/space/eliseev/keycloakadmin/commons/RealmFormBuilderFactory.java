package space.eliseev.keycloakadmin.commons;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import space.eliseev.keycloakadmin.dto.RealmDto;
import space.eliseev.keycloakadmin.service.RealmFormBuilder;
import space.eliseev.keycloakadmin.service.RealmFormBuilderCsv;
import space.eliseev.keycloakadmin.service.RealmFormBuilderXlsx;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class RealmFormBuilderFactory {
    private final RealmFormBuilderCsv realmFormBuilderCsv;
    private final RealmFormBuilderXlsx realmFormBuilderXlsx;
    private final Map<FileType, Supplier<RealmFormBuilder>> map = new EnumMap<>(FileType.class);

    public byte[] download(List<RealmDto> data, String fileType) {
        FileType type;
        try {
            type = RealmFormBuilderFactory.FileType.valueOf(fileType.toUpperCase());
        } catch (IllegalArgumentException e) {
            String error = new StringBuilder().append("Error during defining format(2). Format not found").append("\n")
                    .append(e.getMessage()).append("\n")
                    .append(e.getCause()).append("\n").toString();
            log.error(error);
            throw e;
        }
        Optional<RealmFormBuilder> builder = Optional.ofNullable(map.get(type).get());
        if (builder.isEmpty()) {
            log.error("Error during defining format(1). Format not found");
            throw new IllegalArgumentException("Error during defining format(1)");
        }
        return builder.get().download(data);
    }

    @PostConstruct
    public void init() {
        map.put(FileType.CSV, () -> realmFormBuilderCsv);
        map.put(FileType.XLSX, () -> realmFormBuilderXlsx);
    }

    private enum FileType {
        CSV,
        XLSX
    }
}
