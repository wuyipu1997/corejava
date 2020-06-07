package v2.ch01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * created by wyip on 2020/6/7
 */
public class ParallelStreamTest {

    public static void main(String[] args) {
        //假设你想要好对字符串流中的所有短单词计数
        List<String> words = Arrays.asList("a", "bc", "def", "sadasd", "saasa", "asssssssssssssssssssssss", "b", "c", "n");

        int[] shortWords = new int[12];
        // 错误示范  - 经典的竞争情况
        words.parallelStream().forEach(
                t -> { if (t.length() < 12) shortWords[t.length()]++;}
        );
        System.out.println(Arrays.toString(shortWords));


        //注意，传递给并行流操作的函数不应该被堵塞。并行流使用 fork-join 池来操作流的各个部分。如果多个流操作被阻塞，那么池可能就无法做任何事情了
        //正确示范 - 远离易变状态
        Map<Integer, Long> shortWordCounts =
                words.parallelStream()
                        .filter(s -> s.length() < 10)
                        .collect(Collectors.groupingBy(
                                String::length,
                                Collectors.counting()));
        System.out.println(shortWordCounts);


        // 可以通过放弃排序要求来提高limit方法的速度，如果只想从流中取出任意3个元素，而不在意倒地要获取哪些，那么可以调用：
        words.parallelStream().unordered().limit(3);

        // 合并映射表的代价很高昂。正是因为这个原因，Collectors.groupingByConcurrent 使用了共享的并发映射表
        ConcurrentMap<Integer, List<String>> result =
                words.parallelStream().collect(Collectors.groupingByConcurrent(String::length));
        System.out.println("result: " + result);
        // 当然，如果使用独立于排序的下游收集器，那么就不必在意了
        Map<Integer, Long> wordWordCounts =
                words.parallelStream()
                        .filter(s -> s.length() < 10)
                        .collect(Collectors.groupingByConcurrent(
                                String::length,
                                Collectors.counting()));
        System.out.println(wordWordCounts);
        System.out.println("wordWordCounts: " + wordWordCounts);

        // 为了让并行流正常工作，需要满足大量的条件：
        //  ·数据应该在内存中。必须等到数据到达是非常低效的。
        //  ·流应该可以被高效地分成若干个字部分。由数据或平衡二叉树支撑的流都可以工作的很好，但是Stream.iterate返回的结果不行。
        //  ·流操作的工作量应该具有较大的规模。如果总工作负载并不是很大，那么搭建并行计算时所付出的代价就没有什么意义。
        //  ·流操作不应该被阻塞
        // 换句话说，不要将所有的流都转换为并行流。只有在对已经位于内存中的数据执行大量计算操作时，才应该使用并行流。

    }



}
