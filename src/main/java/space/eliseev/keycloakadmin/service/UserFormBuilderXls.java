package space.eliseev.keycloakadmin.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.entity.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class UserFormBuilderXls implements UserFormBuilder{
    @Override
    public byte[] download(List<User> list) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("All Users List");
        int i = 0;
        Row row = sheet.createRow(i++);
        int c = 0;
        row.createCell(c++).setCellValue("email");
        row.createCell(c++).setCellValue("emailVerified");
        row.createCell(c++).setCellValue("firstName");
        row.createCell(c++).setCellValue("lastName");
        row.createCell(c++).setCellValue("username");
        row.createCell(c).setCellValue("createdTimestamp");
        for (User u : list) {
            row = sheet.createRow(i++);
            c = 0;
            row.createCell(c++).setCellValue(u.getEmail());
            row.createCell(c++).setCellValue(u.getEmailVerified());
            row.createCell(c++).setCellValue(u.getFirstName());
            row.createCell(c++).setCellValue(u.getLastName());
            row.createCell(c++).setCellValue(u.getUsername());
            row.createCell(c).setCellValue(u.getCreatedTimestamp());
        }
        try {
            workbook.setWorkbookType(XSSFWorkbookType.XLSX);
            workbook.write(bos);
        } catch (IOException ioe) {

        }
        return bos.toByteArray();
    }
}
