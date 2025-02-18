package local.ytk.util.function;

import java.util.function.Consumer;

@FunctionalInterface
public interface VarargConsumer<T> extends Consumer<T[]> {
    void accept(T... args);
}
