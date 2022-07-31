package space.eliseev.keycloakadmin.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.ClientDto;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ClientFormBuilderXlsx implements ClientFormBuilder {
    @Override
    public byte[] dowload(List<ClientDto> list) {
        byte[] result;
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream bos = new ByteArrayOutputStream()){
            int countRow = 0;
            int countColumn = 0;
            Sheet sheet = workbook.createSheet("All Client List");
            Row row = sheet.createRow(countRow++);
            row.createCell(countColumn++).setCellValue("enabled");
            row.createCell(countColumn++).setCellValue("clientId");
            row.createCell(countColumn++).setCellValue("publicClient");
            row.createCell(countColumn++).setCellValue("secret");
            row.createCell(countColumn++).setCellValue("realmName");
            row.createCell(countColumn++).setCellValue("protocol");
            row.createCell(countColumn++).setCellValue("name");
            row.createCell(countColumn++).setCellValue("clientAuthenticatorType");
            row.createCell(countColumn).setCellValue("description");

            for(ClientDto clientDto: list) {
                countColumn = 0;
                row = sheet.createRow(countRow++);
                row.createCell(countColumn++).setCellValue(clientDto.getEnabled());
                row.createCell(countColumn++).setCellValue(clientDto.getClientId());
                row.createCell(countColumn++).setCellValue(clientDto.getPublicClient());
                row.createCell(countColumn++).setCellValue(clientDto.getSecret());
                row.createCell(countColumn++).setCellValue(clientDto.getRealmName());
                row.createCell(countColumn++).setCellValue(clientDto.getProtocol());
                row.createCell(countColumn++).setCellValue(clientDto.getName());
                row.createCell(countColumn++).setCellValue(clientDto.getClientAuthenticatorType());
                row.createCell(countColumn).setCellValue(clientDto.getDescription());
            }

            workbook.write(bos);
            result = bos.toByteArray();

        } catch (IOException e) {
            String error = "Error during parsing in XLSX" + "\n" +
                    e.getMessage() + "\n" +
                    e.getCause() + "\n";
            log.error(error);
            throw new BadFileFormatExeption("Error during parsing in XLSX");
        }
        return result;
    }
}
