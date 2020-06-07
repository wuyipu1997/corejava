package v2.ch06;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * created by wyip on 2020/6/7
 */
public class TimeLine {

    public static void main(String[] args) {
        Instant start = Instant.now();
        runAlgorithm();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        long millis = timeElapsed.toMillis();
        System.out.println(millis + " milliseconds");

        Instant start2 = Instant.now();
        runAlgorithm2();
        Instant end2 = Instant.now();
        Duration timeElapsed2 = Duration.between(start2, end2);
        long millis2 = timeElapsed2.toMillis();
        System.out.println(millis2 + " milliseconds");

        boolean overTenTimesFaster = timeElapsed.multipliedBy(10).minus(timeElapsed2).isNegative();
        if (overTenTimesFaster){
            System.out.println("The first algorithm is more than the times faster");
        }else {
            System.out.println("The first algorithm is not more than the times faster");
        }


    }

    private static void runAlgorithm() {
        int size = 10;
        List<Integer> list = new Random().ints().map(i -> i % 100).limit(size)
                .boxed().collect(Collectors.toList());
        Collections.sort(list);
        System.out.println(list);
    }

    private static void runAlgorithm2() {
        int size = 10;
        List<Integer> list = new Random().ints().map(i -> i % 100).limit(size)
                .boxed().collect(Collectors.toList());
        while (!IntStream.range(1, list.size()).allMatch(
                i -> list.get(i - 1).compareTo(list.get(i)) <= 0)){
            Collections.shuffle(list);
        }
        System.out.println(list);
    }


}
