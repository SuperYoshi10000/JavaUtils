package local.ytk.util.function;

import java.util.function.Function;

@FunctionalInterface
public interface ItemSelector<T> extends Function<T[], T> {
    static <T> ItemSelector<T> index(int index) {
        return a -> a[index];
    }
    static <T> ItemSelector<T> first() {
        return a -> a[0];
    }
    static <T> ItemSelector<T> last() {
        return a -> a[a.length - 1];
    }
    static <T> ItemSelector<T> random() {
        return a -> a[(int) Math.floor(Math.random() * a.length)];
    }
}
