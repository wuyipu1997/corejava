package v2.ch06;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * created by wyip on 2020/6/7
 */
public class ZonedTimes {

    public static void main(String[] args) {
        ZonedDateTime appolollaunch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0,
                ZoneId.of("America/New_York"));
        System.out.println("appolollaunch: " + appolollaunch);

        Instant instant = appolollaunch.toInstant();
        System.out.println("instant: " + instant);

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
        System.out.println("zonedDateTime: " + zonedDateTime);




    }

}
