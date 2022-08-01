package space.eliseev.keycloakadmin.commons;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import space.eliseev.keycloakadmin.dto.RealmDto;
import space.eliseev.keycloakadmin.service.RealmFormBuilder;
import space.eliseev.keycloakadmin.service.RealmFormBuilderCsv;
import space.eliseev.keycloakadmin.service.RealmFormBuilderXlsx;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class RealmFormBuilderFactory {
    private final RealmFormBuilderCsv realmFormBuilderCsv;
    private final RealmFormBuilderXlsx realmFormBuilderXlsx;
    private final Map<FileType, Supplier<RealmFormBuilder>> map = new EnumMap<>(FileType.class);
    private final String[] headers = new String[]{
            "Access Code Lifespan",
            "User Action Lifespan",
            "Access Token Lifespan",
            "Enabled",
            "Events Enabled",
            "Name",
            "Not Before",
            "Registration Allowed",
            "Remember Me",
            "Reset Password Allowed",
            "Social",
            "SSL Required",
            "Sso Idle Timeout",
            "Sso Max Lifespan",
            "Update Profile On Soc Login",
            "Verify Email",
            "Login Lifespan",
            "Internationalization Enabled",
            "Reg Email As Username",
            "Admin Events Enabled",
            "Admin Events Details Enabled",
            "Edit Username Allowed",
            "Otp Policy Counter",
            "Otp Policy Window",
            "Otp Policy Period",
            "Otp Policy Digits",
            "Offline Session Idle Timeout",
            "Revoke Refresh Token",
            "Access Token Life Implicit",
            "Login With Email Allowed",
            "Duplicate Emails Allowed",
            "Refresh Token Max Reuse",
            "Allow User Managed Access",
            "Sso Max Lifespan Remember Me",
            "Sso Idle Timeout Remember Me"
    };

    public byte[] download(List<RealmDto> data, String fileType) {
        FileType type;
        try {
            type = RealmFormBuilderFactory.FileType.valueOf(fileType.toUpperCase());
        } catch (IllegalArgumentException e) {
            String error = new StringBuilder().append("Error during defining format(2). Format not found").append("\n")
                    .append(e.getMessage()).append("\n")
                    .append(e.getCause()).append("\n").toString();
            log.error(error);
            throw e;
        }
        Optional<RealmFormBuilder> builder = Optional.ofNullable(map.get(type).get());
        if (builder.isEmpty()) {
            log.error("Error during defining format(1). Format not found");
            throw new IllegalArgumentException("Error during defining format(1)");
        }
        return builder.get().download(data);
    }

    @PostConstruct
    public void init() {
        map.put(FileType.CSV, () -> realmFormBuilderCsv);
        map.put(FileType.XLSX, () -> realmFormBuilderXlsx);
    }

    private enum FileType {
        CSV,
        XLSX
    }
}
