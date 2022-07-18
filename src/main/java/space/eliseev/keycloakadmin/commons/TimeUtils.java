package space.eliseev.keycloakadmin.commons;

import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@UtilityClass
public class TimeUtils {
    public static LocalDateTime toLocalDateTime(Long value) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(value), TimeZone.getDefault().toZoneId());
    }

    public static Long toLong(LocalDateTime value) {
        return Timestamp.valueOf(value).getTime();
    }
}
