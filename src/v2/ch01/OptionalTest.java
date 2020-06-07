package v2.ch01;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * created by wyip on 2020/6/7
 */
public class OptionalTest {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("a", "bc", "def", "g", "hijkl");

        //分别产生这个流的最大元素和最小元素，使用由给定比较器定义的排序规则。这些操作都是终结操作。
        Optional<String> largest = words.stream().max(String::compareToIgnoreCase);
        System.out.println("largest:" + largest.orElse(""));
        Optional<String> minimum = words.stream().min(String::compareToIgnoreCase);
        System.out.println("minimum:" + minimum.orElse(""));

        //分别产生这个流的第一个和任意一个元素，如果这个流为空，会产生一个空的Optional对象。这些操作都是终结操作。
        Optional<String> findFirst = words.stream().filter(s -> s.startsWith("b")).findFirst();
        System.out.println("findFirst:" + findFirst.orElse(""));
        Optional<String> findAny = words.stream().filter(s -> s.startsWith("b")).findAny();
        System.out.println("findAny:" + findAny.orElse(""));

        //分贝在这个流中任意元素、所有元素和没有任何元素匹配给定断言时返回true。这些操作都是终结操作。
        boolean anyMatch = words.stream().anyMatch(t -> t.startsWith("a"));
        System.out.println("anyMatch:" + anyMatch);
        boolean allMatch = words.stream().allMatch(t -> t.startsWith("a"));
        System.out.println("allMatch:" + allMatch);
        boolean noneMatch = words.stream().noneMatch(t -> t.startsWith("a"));
        System.out.println("noneMatch:" + noneMatch);

        Optional<String> createOptionalNull = Optional.ofNullable(null);
        Optional<String> createOptionalNotNull = Optional.ofNullable("test");
        System.out.println("orElse():" + createOptionalNull.orElse(""));
        System.out.println("orElse():" + createOptionalNull.orElseGet(() -> Locale.getDefault().getDisplayName()));
        createOptionalNotNull.orElseThrow(IllegalAccessError::new);
        createOptionalNotNull.ifPresent(t -> System.out.println("ifPresent():" + t));

        if (createOptionalNotNull.isPresent()){
            System.out.println("isPresent():" + createOptionalNotNull.get());
        }

        //产生将mapper应用于当前的Optional值所产生的结果，或者在当前Optional为空时，返回一个空Optional
        System.out.println(inverse(4.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(-1.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(-0.0).flatMap(OptionalTest::squareRoot));
        System.out.println(Optional.of(-4.0).flatMap(OptionalTest::inverse).flatMap(OptionalTest::squareRoot));

    }

    public static Optional<Double> inverse(Double x){
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    /**
     * 计算平方根
     * @param x
     * @return
     */
    public static  Optional<Double> squareRoot(Double x){
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }

}
