package space.eliseev.keycloakadmin.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import space.eliseev.keycloakadmin.commons.TimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class TimeUtilsTest {
    @Test
    void testToLocalDateTime() {
        DateTimeFormatter formatter =  new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("EEE, dd MMM yyyy HH:mm:ss")
                .toFormatter(Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse("Mon, 18 Jul 2022 12:41:30", formatter);
        LocalDateTime time = TimeUtils.toLocalDateTime(1658137290000L);
        Assert.assertEquals(time, localDateTime);
    }

    @Test
    void testToLong() {
        DateTimeFormatter formatter =  new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("EEE, dd MMM yyyy HH:mm:ss")
                .toFormatter(Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse("Mon, 18 Jul 2022 12:41:30", formatter);
        Long time = TimeUtils.toLong(localDateTime);
        Assert.assertEquals(time, 1658137290000L);
    }
}
