package space.eliseev.keycloakadmin.dto;

import lombok.Data;

@Data
public class ClientDto {

    private Boolean enabled;

    // Т.к в классе уже есть поле name, поэтому решил не менять его название на clientName
    private String clientId;

    private Boolean publicClient;

    private String secret;

    // Соответствует текстовому полю realm_id в таблице client
    private String realmName;

    private String protocol;

    private String name;

    private String clientAuthenticatorType;

    private String description;

}
