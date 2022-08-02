package space.eliseev.keycloakadmin.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.RoleDto;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;
import space.eliseev.keycloakadmin.service.RoleFormBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class RoleFormBuilderXlsx implements RoleFormBuilder {
    @Override
    public byte[] download(List<RoleDto> list) {
        byte[] result;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("All Roles List");
            int i = 0;
            Row row = sheet.createRow(i++);
            int c = 0;
            row.createCell(c++).setCellValue("Role Name");
            row.createCell(c++).setCellValue("Client has Role");
            row.createCell(c++).setCellValue("Description");
            row.createCell(c++).setCellValue("Client Name");
            row.createCell(c).setCellValue("Realm Name");
            for (RoleDto r : list) {
                row = sheet.createRow(i++);
                c = 0;
                row.createCell(c++).setCellValue(r.getName());
                row.createCell(c++).setCellValue(r.getClientRole().toString());
                row.createCell(c++).setCellValue(r.getDescription());
                row.createCell(c++).setCellValue(r.getClientName());
                row.createCell(c).setCellValue(r.getRealmName());
            }
            workbook.setWorkbookType(XSSFWorkbookType.XLSX);
            workbook.write(bos);
            result = bos.toByteArray();
        } catch (IOException e) {
            String error = new StringBuilder().append("Error during parsing in XLSX").append("\n")
                    .append(e.getMessage()).append("\n")
                    .append(e.getCause()).append("\n").toString();
            log.error(error);
            throw new BadFileFormatExeption("Error during parsing in XLSX");
        }
        return result;
    }
}
