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

    private String[] getRow(RealmDto dto) {
        return new String[]{
                dto.getAccessCodeLifespan().toString(),
                dto.getUserActionLifespan().toString(),
                dto.getAccessTokenLifespan().toString(),
                dto.getEnabled().toString(),
                dto.getEventsEnabled().toString(),
                dto.getName(),
                dto.getNotBefore().toString(),
                dto.getRegistrationAllowed().toString(),
                dto.getRememberMe().toString(),
                dto.getResetPasswordAllowed().toString(),
                dto.getSocial().toString(),
                dto.getSslRequired(),
                dto.getSsoIdleTimeout().toString(),
                dto.getSsoMaxLifespan().toString(),
                dto.getUpdateProfileOnSocLogin().toString(),
                dto.getVerifyEmail().toString(),
                dto.getLoginLifespan().toString(),
                dto.getInternationalizationEnabled().toString(),
                dto.getRegEmailAsUsername().toString(),
                dto.getAdminEventsEnabled().toString(),
                dto.getAdminEventsDetailsEnabled().toString(),
                dto.getEditUsernameAllowed().toString(),
                dto.getOtpPolicyCounter().toString(),
                dto.getOtpPolicyWindow().toString(),
                dto.getOtpPolicyPeriod().toString(),
                dto.getOtpPolicyDigits().toString(),
                dto.getOfflineSessionIdleTimeout().toString(),
                dto.getRevokeRefreshToken().toString(),
                dto.getAccessTokenLifeImplicit().toString(),
                dto.getLoginWithEmailAllowed().toString(),
                dto.getDuplicateEmailsAllowed().toString(),
                dto.getRefreshTokenMaxReuse().toString(),
                dto.getAllowUserManagedAccess().toString(),
                dto.getSsoMaxLifespanRememberMe().toString(),
                dto.getSsoIdleTimeoutRememberMe().toString()
        };
    }
}
