package space.eliseev.keycloakadmin.commons;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeUtilsTest {
    public static final Long LONG_TIME = 1658137290000L;
    static LocalDateTime localDateTime;

    @BeforeClass
    public static void beforeClass() {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseSensitive()
                .appendPattern("EEE, dd MMM yyyy HH:mm:ss")
                .toFormatter(Locale.ENGLISH);
        localDateTime = LocalDateTime.parse("Mon, 18 Jul 2022 12:41:30", formatter);
    }

    @Test
    public void toLocalDateTime() {
        LocalDateTime time = TimeUtils.toLocalDateTime(LONG_TIME);
        assertEquals(time, localDateTime);
    }

    @Test
    public void toLong() {
        Long time = TimeUtils.toLong(localDateTime);
        assertEquals(LONG_TIME, time);
    }
}