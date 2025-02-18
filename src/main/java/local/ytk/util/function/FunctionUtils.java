package local.ytk.util.function;

import local.ytk.util.annotation.Static;
import local.ytk.util.collection.Mapper;

import java.util.Optional;
import java.util.function.*;

@Static
public final class FunctionUtils {
    private FunctionUtils() {} // Not constructable
    
    // One Argument
    
    public static <T, R> Supplier<R> bind(Function<T, R> function, T t) {
        return () -> function.apply(t);
    }
    public static <T> Runnable bind(Consumer<T> consumer, T t) {
        return () -> consumer.accept(t);
    }
    
    // Two Arguments, Bind Both
    
    public static <T, U, R> Supplier<R> bind(BiFunction<T, U, R> function, T t, U u) {
        return () -> function.apply(t, u);
    }
    public static <T, U> Runnable bind(BiConsumer<T, U> consumer, T t, U u) {
        return () -> consumer.accept(t, u);
    }
    
    // Two Arguments, Bind One
    
    public static <T, U, R> Function<U, R> bind1(BiFunction<T, U, R> function, T t) {
        return u -> function.apply(t, u);
    }
    public static <T, U> Consumer<U> bind1(BiConsumer<T, U> consumer, T t) {
        return u -> consumer.accept(t, u);
    }
    
    public static <T, U, R> Function<T, R> bind2(BiFunction<T, U, R> function, U u) {
        return t -> function.apply(t, u);
    }
    public static <T, U> Consumer<T> bind2(BiConsumer<T, U> consumer, U u) {
        return t -> consumer.accept(t, u);
    }
    
    // Curry
    
    public static <T, U, R> Function<T, Function<U, R>> curry1(BiFunction<T, U, R> function) {
        return t -> u -> function.apply(t, u);
    }
    public static <T, U> Function<T, Consumer<U>> curry1(BiConsumer<T, U> consumer) {
        return t -> u -> consumer.accept(t, u);
    }
    
    public static <T, U, R> Function<U, Function<T, R>> curry2(BiFunction<T, U, R> function) {
        return u -> t -> function.apply(t, u);
    }
    public static <T, U> Function<U, Consumer<T>> curry2(BiConsumer<T, U> consumer) {
        return u -> t -> consumer.accept(t, u);
    }
    
    // Compose
    
    public static <T1, T2> Consumer<T1> compose(Function<T1, T2> before, Consumer<T2> consumer) {
        return a -> consumer.accept(before.apply(a));
    }
    
    public static <T1, U1, T2> BiConsumer<T1, U1> compose(BiFunction<T1, U1, T2> before, Consumer<T2> consumer) {
        return (a, b) -> consumer.accept(before.apply(a, b));
    }
    public static <T1, U1, T2, R> BiFunction<T1, U1, R> compose(BiFunction<T1, U1, T2> before, Function<T2, R> function) {
        return (a, b) -> function.apply(before.apply(a, b));
    }
    
    public static <T1, U1, T2> BiConsumer<T1, U1> compose1(Function<T1, T2> before, BiConsumer<T2, U1> consumer) {
        return (a, b) -> consumer.accept(before.apply(a), b);
    }
    public static <T1, U1, T2, R> BiFunction<T1, U1, R> compose1(Function<T1, T2> before, BiFunction<T2, U1, R> function) {
        return (a, b) -> function.apply(before.apply(a), b);
    }
    
    public static <T1, U1, U2> BiConsumer<T1, U1> compose2(Function<U1, U2> before, BiConsumer<T1, U2> consumer) {
        return (a, b) -> consumer.accept(a, before.apply(b));
    }
    public static <T1, U1, U2, R> BiFunction<T1, U1, R> compose2(Function<U1, U2> before, BiFunction<T1, U2, R> function) {
        return (a, b) -> function.apply(a, before.apply(b));
    }
    
    // Map
    
    public static <T, R> Supplier<R> map(Supplier<T> supplier, Function<T, R> mapper) {
        return () -> mapper.apply(supplier.get());
    }
    public static <T, U, R> Supplier<R> map(Supplier<T> sup1, Supplier<U> sup2, BiFunction<T, U, R> mapper) {
        return () -> mapper.apply(sup1.get(), sup2.get());
    }
    
    public static <T> Runnable map(Supplier<T> supplier, Consumer<T> consumer) {
        return () -> consumer.accept(supplier.get());
    }
    public static <T, U> Runnable map(Supplier<T> sup1, Supplier<U> sup2, BiConsumer<T, U> consumer) {
        return () -> consumer.accept(sup1.get(), sup2.get());
    }
    
    // Flip Arguments
    
    public static <T, U> BiConsumer<U, T> flip(BiConsumer<T, U> consumer) {
        return (u, t) -> consumer.accept(t, u);
    }
    public static <T, U, R> BiFunction<U, T, R> flip(BiFunction<T, U, R> function) {
        return (u, t) -> function.apply(t, u);
    }
    
    // Cast
    public static <R, O> Supplier<O> cast(Supplier<R> supplier) {
        return map(supplier, r -> (O) r);
    }
    public static <T, R, O> Function<T, O> cast(Function<T, R> function) {
        return function.andThen(r -> (O) r);
    }
    public static <T, U, R, O> BiFunction<T, U, O> cast(BiFunction<T, U, R> function) {
        return function.andThen(r -> (O) r);
    }
    public static <I, O> O castValue(I input) {
        return (O) input;
    }
    public static <T> UnaryOperator<T> returnArg(Consumer<T> consumer) {
        return t -> {
            consumer.accept(t);
            return t;
        };
    }
    public static <T, U> BiFunction<T, U, T> returnArg1(BiConsumer<T, U> consumer) {
        return (t, u) -> {
            consumer.accept(t, u);
            return t;
        };
    }
    public static <T, U> BiFunction<T, U, U> returnArg2(BiConsumer<T, U> consumer) {
        return (t, u) -> {
            consumer.accept(t, u);
            return u;
        };
    }
    
    public static <T> UnaryOperator<T> getArg() {
        return t -> t;
    }
    public static <T, U> BiFunction<T, U, T> getArg1() {
        return (t, u) -> t;
    }
    public static <T, U> BiFunction<T, U, U> getArg2() {
        return (t, u) -> u;
    }
    
    public static <T> BinaryOperator<T> getArg1Op() {
        return (t, u) -> t;
    }
    public static <T> BinaryOperator<T> getArg2Op() {
        return (t, u) -> u;
    }
    
    public static Runnable runnable(Runnable runnable) {
        return runnable;
    }
    public static <T> Consumer<T> consumer(Consumer<T> consumer) {
        return consumer;
    }
    public static <T, U> BiConsumer<T, U> biConsumer(BiConsumer<T, U> biConsumer) {
        return biConsumer;
    }
    public static <R> Supplier<R> supplier(Supplier<R> supplier) {
        return supplier;
    }
    public static <T, R> Function<T, R> function(Function<T, R> function) {
        return function;
    }
    public static <T, U, R> BiFunction<T, U, R> biFunction(BiFunction<T, U, R> biFunction) {
        return biFunction;
    }
    
    public static <T> Function<T, Optional<T>> match(Predicate<T> condition) {
        return t -> condition.test(t) ? Optional.of(t) : Optional.empty();
    }
}
