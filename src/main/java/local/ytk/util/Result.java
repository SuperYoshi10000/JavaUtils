package local.ytk.util;

import local.ytk.util.function.ThrowableFunctions;

import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface Result<T> {
    Result<?> FAIL = new Result<>() {
        @Override
        public boolean successful() {
            return false;
        }
        @Override
        public Object value() {
            return null;
        }
    };
    
    T value();
    
    default boolean successful() {
        return true;
    }
    default <U> Result<U> map(Function<T, U> mapper) {
        return new Result<>() {
            @Override
            public U value() {
                return mapper.apply(Result.this.value());
            }
            @Override
            public boolean successful() {
                return Result.this.successful();
            }
        };
    }
    default <U> Result<U> flatMap(Function<T, Result<U>> mapper) {
        return new Result<>() {
            @Override
            public U value() {
                return mapper.apply(Result.this.value()).value();
            }
            @Override
            public boolean successful() {
                return Result.this.successful();
            }
        };
    }
    
    static <T> Result<T> of(T value) {
        return () -> value;
    }
    
    static <T> Result<T> ofOptional(Optional<T> value) {
        return value.map(Result::of).orElse(Result.fail());
    }
    default Optional<T> asOptional() {
        return Optional.ofNullable(value());
    }
    
    static <T> Result<T> of(ThrowableFunctions.ThrowableSupplier<T, ?> supplier) {
        return supplier.tryGet();
    }
    static <A, T> Result<T> of(ThrowableFunctions.ThrowableFunction<A, T, ?> function, A arg) {
        return function.tryApply(arg);
    }
    static <A, B, T> Result<T> of(ThrowableFunctions.ThrowableBiFunction<A, B, T, ?> function, A arg1, B arg2) {
        return function.tryApply(arg1, arg2);
    }
    @SuppressWarnings("unchecked")
    static <T> Result<T> fail() {
        return (Result<T>) FAIL;
    }
}
