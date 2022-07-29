package space.eliseev.keycloakadmin.service;

import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserFormBuilderCsv implements UserFormBuilder {
    private final UserServiceImpl userService;

    @Override
    public byte[] download(List<UserDto> dtos) {
        byte[] result;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             CSVWriter writer = new CSVWriter(new OutputStreamWriter(bos), ';', '"', '\\', "\n")) {
            List<String[]> csv = new ArrayList<>();
            String[] headers = "Email;Email Verified;First Name;Last Name;Username;Created Timestamp".split(";");
            csv.add(headers);
            for (UserDto dto : dtos) {
                String[] row = new String[]{
                        dto.getEmail(),
                        dto.getEmailVerified().toString(),
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getUsername(),
                        dto.getCreatedTimestampLocalDateTime().toString()
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
