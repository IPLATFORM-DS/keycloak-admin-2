package space.eliseev.keycloakadmin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.UserDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFormBuilderCsv implements UserFormBuilder {
    private final UserServiceImpl userService;

    @Override
    public byte[] download(List<UserDto> dtos) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Email").append(";").append("Email Verified").append(";")
                .append("First Name").append(";").append("Last Name").append(";")
                .append("Username").append(";").append("Created Timestamp")
                .append("\r\n");
        for (UserDto dto : dtos) {
            stringBuilder.append(dto.getEmail()).append(";")
                    .append(dto.getEmailVerified()).append(";")
                    .append(dto.getFirstName()).append(";")
                    .append(dto.getLastName()).append(";")
                    .append(dto.getUsername()).append(";")
                    .append(dto.getCreatedTimestampLocalDateTime())
                    .append("\r\n");
        }
        return stringBuilder.toString().getBytes();
    }
}
