package space.eliseev.keycloakadmin.dto;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private Boolean emailVerified;
    private String firstName;
    private String lastName;
    // Соответствует текстовому полю realm_id в таблице realm
    private String realmName;
    private String username;
    private Long createdTimestamp;
}
