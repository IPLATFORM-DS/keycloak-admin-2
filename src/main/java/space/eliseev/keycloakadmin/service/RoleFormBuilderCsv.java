package space.eliseev.keycloakadmin.service;

import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.RoleDto;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;
import space.eliseev.keycloakadmin.service.RoleFormBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleFormBuilderCsv implements RoleFormBuilder {

    @Override
    public byte[] download(List<RoleDto> dtos) {
        byte[] result;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             CSVWriter writer = new CSVWriter(new OutputStreamWriter(bos), ';', '"', '\\', "\n")) {
            List<String[]> csv = new ArrayList<>();
            String[] headers = "Name;Client has Role;Description;Client Name;Role Name".split(";");
            csv.add(headers);
            for (RoleDto dto : dtos) {
                String[] row = new String[]{
                        dto.getName(),
                        dto.getClientRole().toString(),
                        dto.getDescription(),
                        dto.getClientName(),
                        dto.getRealmName(),
                };
                csv.add(row);
            }
            writer.writeAll(csv);
            writer.flush();
            result = bos.toByteArray();
        } catch (IOException e) {
            String error = new StringBuilder().append("Error during parsing in CSV").append("\n")
                    .append(e.getMessage()).append("\n")
                    .append(e.getCause()).append("\n").toString();
            log.error(error);
            throw new BadFileFormatExeption("Error during parsing in CSV");
        }
        return result;
    }
}
