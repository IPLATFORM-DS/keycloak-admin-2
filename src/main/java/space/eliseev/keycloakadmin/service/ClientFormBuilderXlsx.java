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
            Sheet sheet = workbook.createSheet("All Client List");
            Row row = sheet.createRow(countRow++);
            row.createCell(0).setCellValue("enabled");
            row.createCell(1).setCellValue("clientId");
            row.createCell(2).setCellValue("publicClient");
            row.createCell(3).setCellValue("secret");
            row.createCell(4).setCellValue("realmName");
            row.createCell(5).setCellValue("protocol");
            row.createCell(6).setCellValue("name");
            row.createCell(7).setCellValue("clientAuthenticatorType");
            row.createCell(8).setCellValue("description");

            for(ClientDto clientDto: list) {
                row = sheet.createRow(countRow++);
                row.createCell(0).setCellValue(clientDto.getEnabled());
                row.createCell(1).setCellValue(clientDto.getClientId());
                row.createCell(2).setCellValue(clientDto.getPublicClient());
                row.createCell(3).setCellValue(clientDto.getSecret());
                row.createCell(4).setCellValue(clientDto.getRealmName());
                row.createCell(5).setCellValue(clientDto.getProtocol());
                row.createCell(6).setCellValue(clientDto.getName());
                row.createCell(7).setCellValue(clientDto.getClientAuthenticatorType());
                row.createCell(8).setCellValue(clientDto.getDescription());
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
