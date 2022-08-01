package space.eliseev.keycloakadmin.service;

import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.commons.GetObjectAsRow;
import space.eliseev.keycloakadmin.dto.RealmDto;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RealmFormBuilderCsv implements RealmFormBuilder {
    private final GetObjectAsRow asRow;

    @Override
    public byte[] download(List<RealmDto> list) {
        byte[] result;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             CSVWriter writer = new CSVWriter(new OutputStreamWriter(bos), ';', '"', '\\', "\n")) {
            List<String[]> csv = new ArrayList<>();
            csv.add(asRow.getRealmHeaders());
            for (RealmDto dto : list) {
                String[] row = asRow.getRealmAsRow(dto);
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
