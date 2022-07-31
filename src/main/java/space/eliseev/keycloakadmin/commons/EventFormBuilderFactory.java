package space.eliseev.keycloakadmin.commons;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import space.eliseev.keycloakadmin.dto.EventDto;
import space.eliseev.keycloakadmin.service.EventFormBuilder;
import space.eliseev.keycloakadmin.service.EventFormBuilderCsv;
import space.eliseev.keycloakadmin.service.EventFormBuilderXlsx;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventFormBuilderFactory {
    private final EventFormBuilderCsv eventFormBuilderCsv;
    private final EventFormBuilderXlsx eventFormBuilderXlsx;
    private final Map<FileType, Supplier<EventFormBuilder>> map = new EnumMap<>(FileType.class);

    public byte[] download(List<EventDto> data, String fileType) {
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
        Optional<EventFormBuilder> builder = Optional.ofNullable(map.get(type).get());
        if (builder.isEmpty()) {
            log.error("Error during defining format(1). Format not found");
            throw new IllegalArgumentException("Error during defining format(1)");
        }
        return builder.get().download(data);
    }

    @PostConstruct
    public void init() {
        map.put(EventFormBuilderFactory.FileType.CSV, () -> eventFormBuilderCsv);
        map.put(EventFormBuilderFactory.FileType.XLSX, () -> eventFormBuilderXlsx);
    }

    private enum FileType {
        XLSX,
        CSV
    }
}



