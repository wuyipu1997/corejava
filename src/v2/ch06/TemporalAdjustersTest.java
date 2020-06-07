package v2.ch06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * created by wyip on 2020/6/7
 */
public class TemporalAdjustersTest {

    public static void main(String[] args) {
        // 计算诸如“每个月的第一个星期二”.下面是计算2020年6个月的第一个星期二
        LocalDate firstTuesday = LocalDate.of(2020, 6, 1).with(
                TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY));
        System.out.println("firstTuesday: " + firstTuesday);

        // 可以通过实现 TemporalAdjuster 接口来创建自己的调整器。下面是用于计算下一个工作日的调整器。
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.ofDateAdjuster(w -> {
            LocalDate result = w;
            do {
                result = result.plusDays(1);
            } while (result.getDayOfWeek().getValue() >= 6);
            return result;
        });

    }

}
