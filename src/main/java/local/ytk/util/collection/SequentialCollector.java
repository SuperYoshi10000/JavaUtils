package local.ytk.util.collection;

import local.ytk.util.function.FunctionUtils;

import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;

public interface SequentialCollector<T, A> extends Collector<T, A, A> {
    static <T, A> SequentialCollector<T, A> createFlipped(Supplier<A> supplier, BiConsumer<T, A> accumulator) {
        return create(supplier, FunctionUtils.flip(accumulator));
    }
    static <T, A> SequentialCollector<T, A> create(Supplier<A> supplier, BiConsumer<A, T> accumulator) {
        return new SequentialCollector<T, A>() {
            @Override
            public Supplier<A> supplier() {
                return supplier;
            }
            @Override
            public BiConsumer<A, T> accumulator() {
                return accumulator;
            }
        };
    }

    @Override
    default BinaryOperator<A> combiner() {
        return (a, b) -> a;
    }

    @Override
    default UnaryOperator<A> finisher() {
        return a -> a;
    }

    @Override
    default Set<Characteristics> characteristics() {
        return Set.of();
    }
    
}
