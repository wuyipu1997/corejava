package v2.ch01;

import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * created by wyip on 2020/6/7
 */
public class CollectingResults {

    public static <T> void show(String lable, Set<T> set){
        System.out.println(lable + ":" +set.getClass().getName());
        System.out.println("["
                +set.stream().limit(10).map(Object::toString)
                .collect(Collectors.joining(",")) + "]");
    }

    public static void main(String[] args) {
        Iterator<Integer> iterator = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        Integer[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray(Integer[]::new);
        System.out.println("Integer array :" + numbers);

        Set<Double> set = Stream.generate(Math::random).limit(10).collect(Collectors.toSet());
        show("set", set);

        TreeSet<Double> treeSet = Stream.generate(Math::random).limit(10).collect(Collectors.toCollection(TreeSet::new));
        show("treeSet", treeSet);

        System.out.println("joining(): " + Stream.of("1", "3", "2", "6").collect(Collectors.joining()));
        System.out.println("joining(): " + Stream.of("1", "3", "2", "6").collect(Collectors.joining(",")));

        //产生能够生成（Int|Long|Double）SummaryStatistics对象的收集器，通过它可以获得将mapper应用于每个元素后所产生的结果的个数、总和、平均值、最大值和最小值
        IntSummaryStatistics summary =
                Stream.of("abcdefg", "a", "csdsda", "2sas").collect(Collectors.summarizingInt(String::length));
        double averageWordLength = summary.getAverage();
        int max = summary.getMax();
        System.out.println("Average word length: "+ averageWordLength);
        System.out.println("Max word length: "+ max);

    }

}
