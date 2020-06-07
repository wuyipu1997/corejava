package v2.ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * created by wyip on 2020/6/7
 */
public class OtherStreamTransfer {

    public static void main(String[] args) {
        //产生一个流，它是通过将mapper应用于当前流中所有元素锁产生的结果连接到一起而获得的。
        List<String> flatMap = Arrays.asList("abc", "def", "ghi").stream()
                .flatMap(t -> {
                    List<String> result = new ArrayList<>();
                    for (int i = 0; i < t.length(); i++) {
                        result.add(t.substring(i, i + 1));
                    }
                    return result.stream();
                }).collect(Collectors.toList());
        System.out.println(flatMap);

        // 产生一个流，它的元素是按当前流中所有元素按照顺序排列的。
        List<String> collect = Stream.of("as", "asdsadsadsad", "sasadasd", "1234")
                .sorted(Comparator.comparing(String::length).reversed())
                .collect(Collectors.toList());
        System.out.println(collect);

        Object[] powers = Stream.iterate(1.0, p -> p * 2)
                .peek(e -> System.out.println("Feching " + e))
                .limit(20).toArray();

    }

}
