package v2.ch06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * created by wyip on 2020/6/7
 */
public class Formatting {

    public static void main(String[] args) {
        ZonedDateTime appolollaunch = ZonedDateTime.of(1996, 7, 16, 9, 32, 0, 0,
                ZoneId.of("America/New_York"));

        String formatted = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(appolollaunch);
        System.out.println(formatted);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        formatted = formatter.format(appolollaunch);
        System.out.println(formatted);

        formatted = formatter.withLocale(Locale.FRENCH).format(appolollaunch);
        System.out.println(formatted);

        formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
        formatted = formatter.format(appolollaunch);
        System.out.println(formatted);

        LocalDate churchsBirthday = LocalDate.parse("1903-06-14");
        System.out.println("churchsBirthday: " + churchsBirthday);
        appolollaunch = ZonedDateTime.parse("1969-07-16 03:32:00-0400",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
        System.out.println("appolollaunch: " + appolollaunch);

        for (DayOfWeek week : DayOfWeek.values()) {
            System.out.println(week.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + "");
        }

    }

}
