package space.eliseev.keycloakadmin.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class ClientDto {

    @CsvBindByPosition(position = 0)
    private Boolean enabled;

    // Т.к в классе уже есть поле name, поэтому решил не менять его название на clientName
    @CsvBindByPosition(position = 1)
    private String clientId;

    @CsvBindByPosition(position = 2)
    private Boolean publicClient;

    @CsvBindByPosition(position = 3)
    private String secret;

    // Соответствует текстовому полю realm_id в таблице client
    @CsvBindByPosition(position = 4)
    private String realmName;

    @CsvBindByPosition(position = 5)
    private String protocol;

    @CsvBindByPosition(position = 6)
    private String name;

    @CsvBindByPosition(position = 7)
    private String clientAuthenticatorType;

    @CsvBindByPosition(position = 8)
    private String description;

}
