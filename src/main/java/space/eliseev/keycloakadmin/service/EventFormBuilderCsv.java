package space.eliseev.keycloakadmin.service;

import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.EventDto;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventFormBuilderCsv implements EventFormBuilder {
    private final EventServiceImpl eventService;

    @Override
    public byte[] download(List<EventDto> list) {
        byte[] result;
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            CSVWriter writer = new CSVWriter(new OutputStreamWriter(bos), ';', '"', '\\', "\n")) {
            List<String[]> csv = new ArrayList<>();
            String[] headers = "Username; Details Json; Error; IP Address; Event Time; Type".split(";");
            csv.add(headers);
            for (EventDto dto : list) {
                String[] row = new String[] {
                        dto.getUserName(),
                        dto.getDetailsJson(),
                        dto.getError(),
                        dto.getIpAddress(),
                        dto.getEventTime().toString(),
                        dto.getType()
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
