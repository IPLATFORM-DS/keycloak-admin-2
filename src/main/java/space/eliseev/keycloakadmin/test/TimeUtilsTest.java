package space.eliseev.keycloakadmin.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import space.eliseev.keycloakadmin.commons.TimeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TimeUtilsTest {
    @Test
    void testToLocalDateTime() {
        Map<Long, LocalDateTime> map = new HashMap<>();
        DateTimeFormatter formatter =  new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("EEE, dd MMM yyyy HH:mm:ss")
                .toFormatter(Locale.ENGLISH);
        LocalDateTime time = TimeUtils.toLocalDateTime(1658137290000L);
        map.put(1658137290000L, LocalDateTime.parse("Mon, 18 Jul 2022 12:41:30", formatter));
        Assert.assertEquals(time, map.get(1658137290000L));
    }

    @Test
    void testToLong() {
        Map<LocalDateTime, Long> map = new HashMap<>();
        DateTimeFormatter formatter =  new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("EEE, dd MMM yyyy HH:mm:ss")
                .toFormatter(Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse("Mon, 18 Jul 2022 12:41:30", formatter);
        Long time = TimeUtils.toLong(localDateTime);
        map.put(localDateTime, time);
        Assert.assertEquals(time, map.get(localDateTime));
    }
}
