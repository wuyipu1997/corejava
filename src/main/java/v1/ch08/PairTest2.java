package v1.ch08;


import java.io.Serializable;
import java.time.LocalDate;

/**
 * created by wyip on 2020/6/8
 */
public class PairTest2 {


    public static void main(String[] args) {
        LocalDate[] birthday = {
                LocalDate.of(1906, 12, 9),
                LocalDate.of(1815, 12, 10),
                LocalDate.of(1903, 12, 3),
                LocalDate.of(1910, 6, 22),
        };
        Pair<LocalDate> mm = ArrayAlg.minmax(birthday);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }

    static class ArrayAlg {

        // 为什么使用关键字 extends 而不是 implements?
        // <T extends BoudingType> 表示T应该是绑定类型的子类型。T和绑定类型可以是类，也可以是接口。一个类型变量或通配符可以有多个限定，例如 T extends Comparable & Serializable
        public static <T extends Comparable & Serializable> Pair<T> minmax(T[] a) {
            if (a == null || a.length == 0) {
                return null;
            }
            T min = a[0];
            T max = a[0];
            for (int i = 1; i < a.length; i++) {
                if (min.compareTo(a[i]) > 0) {
                    min = a[i];
                }
                if (max.compareTo(a[i]) < 0) {
                    max = a[i];
                }
            }
            return new Pair<>(min, max);
        }

    }

}
