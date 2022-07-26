package space.eliseev.keycloakadmin.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.UserDto;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class UserFormBuilderXlsx implements UserFormBuilder {
    @Override
    public byte[] download(List<UserDto> list) {
        byte[] result;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFCreationHelper createHelper = workbook.getCreationHelper();
            XSSFSheet sheet = workbook.createSheet("All Users List");
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
            int i = 0;
            Row row = sheet.createRow(i++);
            int c = 0;
            row.createCell(c++).setCellValue("Email");
            row.createCell(c++).setCellValue("Email Verified");
            row.createCell(c++).setCellValue("First Name");
            row.createCell(c++).setCellValue("Last Name");
            row.createCell(c++).setCellValue("Username");
            row.createCell(c).setCellValue("Created Timestamp");
            for (UserDto u : list) {
                row = sheet.createRow(i++);
                c = 0;
                row.createCell(c++).setCellValue(u.getEmail());
                row.createCell(c++).setCellValue(u.getEmailVerified());
                row.createCell(c++).setCellValue(u.getFirstName());
                row.createCell(c++).setCellValue(u.getLastName());
                row.createCell(c++).setCellValue(u.getUsername());
                Cell cellDateTime = row.createCell(c);
                cellDateTime.setCellValue(u.getCreatedTimestampLocalDateTime());
                cellDateTime.setCellStyle(cellStyle);
            }
            workbook.setWorkbookType(XSSFWorkbookType.XLSX);
            workbook.write(bos);
            result = bos.toByteArray();
        } catch (IOException ioe) {
            throw new BadFileFormatExeption("Ошибка в процессе перевода в формат xlsx");
        }
        return result;
    }
}
