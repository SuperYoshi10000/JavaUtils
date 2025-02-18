package local.ytk.util.function;

import java.util.function.Function;

@FunctionalInterface
public interface VarargFunction<T, R> extends Function<T[], R> {
    R apply(T... args);
}
