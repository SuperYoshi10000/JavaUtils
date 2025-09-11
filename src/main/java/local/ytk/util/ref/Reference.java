package local.ytk.util.ref;
import java.util.function.UnaryOperator;
public interface Reference<T> {
    void set(T Value);
    T get();
    Reference<T> modify(UnaryOperator<T> op);
    <U> Reference<U> map(Function<T, U> mapper);

    static <T> Reference<T> of(T value) {
        return new Value(value);
    }
    private static class Value<T> {
        T value;
        Value(T value) {
            this.value = value;
        }
    }
}
