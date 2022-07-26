package space.eliseev.keycloakadmin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFormBuilderCsv implements UserFormBuilder{
    private final UserServiceImpl userService;
    @Override
    public byte[] download(List<User> dtos)  {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("email").append(";").append("emailVerified").append(";")
                .append("firstName").append(";").append("lastName").append(";")
                .append("username").append(";").append("createdTimestamp")
                .append("\r\n");
        for (User dto : dtos) {
            stringBuilder.append(dto.getEmail()).append(";")
                    .append(dto.getEmailVerified()).append(";")
                    .append(dto.getFirstName()).append(";")
                    .append(dto.getLastName()).append(";")
                    .append(dto.getUsername()).append(";")
                    .append(dto.getCreatedTimestamp())
                    .append("\r\n");
        }
        return stringBuilder.toString().getBytes();
    }
}
