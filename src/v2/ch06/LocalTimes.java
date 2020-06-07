package v2.ch06;

import java.time.LocalTime;

/**
 * created by wyip on 2020/6/7
 */
public class LocalTimes {

    public static void main(String[] args) {
        LocalTime rightNow = LocalTime.now();
        LocalTime bedtime = LocalTime.of(22, 30);
        System.out.println("rightNow: " + rightNow);
        System.out.println("bedtime: " + bedtime);

        LocalTime wakeup = bedtime.plusHours(8);
        System.out.println("wakeup: " + wakeup);


    }

}
