package space.eliseev.keycloakadmin.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
import org.springframework.stereotype.Service;
import space.eliseev.keycloakadmin.dto.EventDto;
import space.eliseev.keycloakadmin.exception.BadFileFormatExeption;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class EventFormBuilderXlsx implements EventFormBuilder {

    @Override
    public byte[] download(List<EventDto> list) {
        byte[] result;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFCreationHelper createHelper = workbook.getCreationHelper();
            XSSFSheet sheet = workbook.createSheet("All Event List");
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
            int i = 0;
            Row row = sheet.createRow(i++);
            int c = 0;
            row.createCell(c++).setCellValue("Username");
            row.createCell(c++).setCellValue("Details Json");
            row.createCell(c++).setCellValue("Error");
            row.createCell(c++).setCellValue("IP Address");
            row.createCell(c++).setCellValue("Event Time");
            row.createCell(c).setCellValue("Type");
            for (EventDto event : list) {
                row = sheet.createRow(i++);
                c = 0;
                row.createCell(c++).setCellValue(event.getUserName());
                row.createCell(c++).setCellValue(event.getDetailsJson());
                row.createCell(c++).setCellValue(event.getError());
                row.createCell(c++).setCellValue(event.getIpAddress());
                Cell cellDateTime = row.createCell(c++);
                cellDateTime.setCellValue(event.getEventTime());
                cellDateTime.setCellStyle(cellStyle);
                row.createCell(c).setCellValue(event.getType());
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
