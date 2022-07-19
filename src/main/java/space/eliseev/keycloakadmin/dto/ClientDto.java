package space.eliseev.keycloakadmin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {

    private Boolean enabled;

    private String clientName;

    private Boolean publicClient;

    private String secret;

    private String realmName;

    private String protocol;

    private String name;

    private String clientAuthenticatorType;

    private String description;

}
