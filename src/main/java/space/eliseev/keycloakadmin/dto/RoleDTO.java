package space.eliseev.keycloakadmin.dto;

import lombok.Data;

@Data
public class RoleDTO {
    private String name;
    private Boolean clientRole;
    private String description;
    // Соответствует текстовому полю client_id в таблице client
    private String clientName;
    // Соответствует текстовому полю name в таблице realm
    private String realmName;
}
