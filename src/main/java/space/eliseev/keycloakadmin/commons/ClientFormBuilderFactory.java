package space.eliseev.keycloakadmin.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import space.eliseev.keycloakadmin.dto.ClientDto;
import space.eliseev.keycloakadmin.service.ClientFormBuilder;
import space.eliseev.keycloakadmin.service.ClientFormBuilderCsv;
import space.eliseev.keycloakadmin.service.ClientFormBuilderXlsx;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClientFormBuilderFactory {

    private final ClientFormBuilderCsv clientFormBuilderCsv;
    private final ClientFormBuilderXlsx clientFormBuilderXlsx;
    private final Map<String, Supplier<ClientFormBuilder>> map = new HashMap<>();

    @PostConstruct
    public void init() {
        map.put(DocumentType.CSV.getName(), () -> clientFormBuilderCsv);
        map.put(DocumentType.XLSX.getName(), () -> clientFormBuilderXlsx);
    }

    public byte[] download (String filetype, List<ClientDto> list) {
        return Optional.ofNullable(map.get(filetype))
                .map(formBuilder -> {
                    try {
                        return formBuilder.get().dowload(list);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .orElseThrow(() -> {
                    log.error("Error during defining format. Format not found");
                    return new IllegalArgumentException("Error during defining format. Format not found");
                });
    }


    @Getter
    @AllArgsConstructor
    private enum DocumentType {
        CSV("csv"),
        XLSX("xlsx");
        private final String name;
    }
}
