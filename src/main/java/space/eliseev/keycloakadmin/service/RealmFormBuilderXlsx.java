package space.eliseev.keycloakadmin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.commons.GetObjectAsRow;
import space.eliseev.keycloakadmin.dto.RealmDto;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RealmFormBuilderXlsx implements RealmFormBuilder {
    private final GetObjectAsRow asRow;

    @Override
    public byte[] download(List<RealmDto> list) {
        byte[] result;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("All Realms List");
            int i = 0;
            Row row = sheet.createRow(i++);
            int c = 0;
            for (String header : asRow.getRealmHeaders()) {
                row.createCell(c++).setCellValue(header);
            }
            for (RealmDto realmDto : list) {
                row = sheet.createRow(i++);
                c = 0;
                for (String cell : asRow.getRealmAsRow(realmDto)) {
                    row.createCell(c++).setCellValue(cell);
                }
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
