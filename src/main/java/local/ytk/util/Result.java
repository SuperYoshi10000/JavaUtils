package local.ytk.util;

import local.ytk.util.function.ThrowingFunctions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

@FunctionalInterface
public interface Result<T> extends Supplier<T> {
    default boolean successful() {
        return true;
    }
    
    static <T> Result<T> of(T value) {
        return () -> value;
    }
    static <T> Result<T> ofNonnull(T value) {
        return value != null ? of(value) : Result.failure();
    }
    
    static <T> Result<T> ofNullableOptional(Optional<T> value) {
        return value.map(Result::of).orElse(Result.of(null));
    }
    static <T> Result<T> ofNonnullOptional(Optional<T> value) {
        return value.map(Result::of).orElse(Result.failure());
    }
    
    static <T> Result<T> of(ThrowingFunctions.ThrowingSupplier<T, ?> supplier) {
        return supplier.tryGet();
    }
    static <A, T> Result<T> of(ThrowingFunctions.ThrowingFunction<A, T, ?> function, A arg) {
        return function.tryApply(arg);
    }
    static <A, B, T> Result<T> of(ThrowingFunctions.ThrowingBiFunction<A, B, T, ?> function, A arg1, B arg2) {
        return function.tryApply(arg1, arg2);
    }
    
    
    @Nullable T get();
    
    default @NotNull T getNonNull() {
        T value = get();
        if (value == null) throw new NullPointerException("Result is null");
        return value;
    }
    
    default String message() {
        return String.valueOf(get());
    }
    
    default boolean failed() {
        return !successful();
    }
    
    default Result<@NotNull T> failIfNull() {
        return get() == null ? Result.failure() : this;
    }
    
    default T getOrThrow() throws NoSuchElementException {
        if (failed()) try {
            throwException(); // Should always throw
        } catch (Throwable e) {
            throw new NoSuchElementException(e);
        }
        return get();
    }
    
    default Optional<T> optional() {
        return Optional.ofNullable(get());
    }
    default Stream<T> stream() {
        return Stream.ofNullable(get());
    }
    default <R> Result<R> map(Function<T, R> mapper) {
        if (failed()) return failure();
        return Result.success(mapper.apply(get()));
    }
    default <R> Result<R> flatMap(Function<T, Result<R>> mapper) {
        if (failed()) return failure();
        return mapper.apply(get());
    }
    default T orElse(T other) {
        if (failed()) return other;
        return this.get();
    }
    
    default void throwException() throws Throwable {
        // Don't throw when successful
    }
    
    
    static <T> Result<T> success(@Nullable T result) {
        return new Success<>(result);
    }
    static <T> Result<T> from(Optional<T> optional) {
        return optional.map(Result::success).orElse(Result.failure());
    }
    static <T> Result<T> ofNullable(@Nullable T value) {
        return value == null ? Result.failure() : Result.success(value);
    }
    
    @SuppressWarnings("unchecked")
    static <T> Result<T> failure() {
        return (Result<T>) FAILURE;
    }
    static <T> Result<T> failure(String message) {
        return new Failed<>(new Exception(message));
    }
    static <T> Result<T> failure(Throwable exception) {
        return new Failed<>(exception);
    }
    
    static <T> Result<T> attempt(Supplier<T> supplier) {
        try {
            return success(supplier.get());
        } catch (Throwable e) {
            return failure(e);
        }
    }
    static <T, E extends Throwable> Result<T> attempt(ThrowingFunctions.ThrowingSupplier<T, E> supplier) {
        try {
            return success(supplier.get());
        } catch (Throwable e) {
            return failure(e);
        }
    }
    
    static <T> Result<List<T>> some(Collection<Result<T>> results) {
        List<T> values = results.stream().flatMap(Result::stream).toList();
        return values.isEmpty() ? Result.failure() : Result.success(values);
    }
    static <T> Result<List<T>> all(Collection<Result<T>> results) {
        if (results.stream().anyMatch(Result::failed)) return Result.failure(results.stream().filter(Result::failed).reduce("", (String a, Result<T> b) -> a + "\n" + b.message(), String::concat));
        return Result.success(results.stream().map(Result::get).toList());
    }
    
    Result<?> FAILURE = new Failed<>(null);
    record Success<T>(@Nullable T value) implements Result<T> {
        @Override
        public T get() {
            return value;
        }
        
        @Override
        public @NotNull T getNonNull() {
            if (value == null) throw new NullPointerException("Result is null");
            return value;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof Success<?>(Object otherValue))) return false;
            return Objects.equals(this.value, otherValue);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
        
        @Override
        public @NotNull String toString() {
            return "Result.Success(" + value + ")";
        }
    }
    
    record Failed<T, E extends Throwable>(E exception) implements Result<T> {
        @Override
        public @Nullable T get() {
            return null;
        }
        
        @Override
        public @NotNull T getNonNull() {
            throw exception == null ? new NullPointerException() : new NullPointerException(exception.getMessage());
        }
        
        @Override
        public boolean successful() {
            return false;
        }
        
        @Override
        public Result<T> failIfNull() {
            return this;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof Failed<?, ?>(Exception other))) return false;
            return Objects.equals(this.exception, other);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(exception);
        }
        
        @Override
        public @NotNull String toString() {
            return "Result.Failed(" + nonnullMessage() + ")";
        }
        
        public String message() {
            return exception.getMessage();
        }
        
        public String nonnullMessage() {
            return exception == null ? "" : message();
        }
        
        public void throwException() throws E {
            throw exception;
        }
    }
}
