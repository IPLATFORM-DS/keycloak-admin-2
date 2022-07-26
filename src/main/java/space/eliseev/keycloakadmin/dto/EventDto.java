package space.eliseev.keycloakadmin.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {
    // Соответствует текстовому полю client_id в таблице event
    private String clientName;
    // Соответствует текстовому полю realm_id в таблице event
    private String realmName;
    // Соответствует текстовому полю user_id в таблице event
    private String userName;
    private String detailsJson;
    private String error;
    private String ipAddress;
    private LocalDateTime eventTime;
    private String type;
}
