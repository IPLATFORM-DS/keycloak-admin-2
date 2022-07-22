package space.eliseev.keycloakadmin.test;

import org.junit.BeforeClass;
import org.junit.Test;
import space.eliseev.keycloakadmin.commons.TimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class TimeUtilsTest {
    static LocalDateTime localDateTime;
    public static final Long LONG_TIME = 1658137290000L;

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
        assertEquals(time, LONG_TIME);
    }
}