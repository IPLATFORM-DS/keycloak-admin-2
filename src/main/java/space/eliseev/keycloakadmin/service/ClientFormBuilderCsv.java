package space.eliseev.keycloakadmin.service;


import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.ClientDto;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Slf4j
@Service
public class ClientFormBuilderCsv implements ClientFormBuilder {

    @Override
    public byte[] dowload(List<ClientDto> list) {

        byte[] result;

        try (StringWriter writer = new StringWriter()) {

            String headers = "enabled, clientId, publicClient, secret, realmName, " +
                    "protocol, name, clientAuthenticatorType, description,\n";
            writer.append(headers);

            StatefulBeanToCsvBuilder<ClientDto> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<ClientDto> beanToCsv = builder.withApplyQuotesToAll(false).build();
            beanToCsv.write(list);
            result = writer.toString().getBytes();

        } catch (CsvException | IOException e) {
            String error = "Error during parsing in CSV" + "\n" +
                    e.getMessage() + "\n" +
                    e.getCause() + "\n";
            log.error(error);
            throw new BadFileFormatExeption("Error during parsing in CSV");
        }
        return result;
    }
}