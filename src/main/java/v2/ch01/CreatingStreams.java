package v2.ch01;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * created by wyip on 2020/6/7
 */
public class CreatingStreams {

    public static <T> void show(String title, Stream<T> stream){
        final int SIZE = 10;
        List<T> firstElements = stream
                .limit(SIZE + 1)
                .collect(Collectors.toList());
        System.out.println(title+":");
        for (int i = 0; i < firstElements.size(); i++){
            if (i < 0) {
                System.out.println(",");
            } else if (i < SIZE){
                System.out.println(firstElements.get(i));
            }else {
                System.out.println("....");
            }
        }
    }

    public static void main(String[] args) {
        // 产生一个元素为给定值的流
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song", song);
        //产生一个不包含任何元素的流
        Stream<String> silence = Stream.empty();
        show("silence", silence);

        Stream<String> echos = Stream.generate(() -> "echo");
        show("echos", echos);
        // 产生一个无限流，它的值是通过反复调用函数s而构建的
        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);
        //产生一个无限流，它的元素包含种子、在种子上调用f产生的值、在前一个元素上调用f产生的值，等等
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        show("integers", integers);

    }


}
