package local.ytk.util.ref;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.Optional;

public interface Box<T> {
    void set(T value);
    T get();
    default T modify(UnaryOperator<T> op) {
        T next = op.apply(get());
        set(next);
        return next;
    }
    <R> Box<R> map(Function<T, R> mapper);
    default <R> R convert(Function<T, R> mapper) {
        return mapper.apply(get());
    }
    T merge(T other, BinaryOperator<T> op);
    default Optional<T> optional() {
        return Optional.ofNullable(get());
    }
    default Box<T> copy() {
        return create(get());
    }
    default Box<T> create(T value) {
        return of(value);
    }

    static <T> Box<T> of(T value) {
        return new Value<>(value);
    }
    static <T> Box<T> atomic(T value) {
        return new Value<>(value);
    }
    class Value<T> implements Box<T> {
        private T value;
        Value(T value) {
            this.value = value;
        }
        public void set(T value) {
            this.value = value;
        }
        public T get() {
            return this.value;
        }
        public T modify(UnaryOperator<T> op) {
            return this.value = op.apply(this.value);
        }
        public <R> Box<R> map(Function<T, R> mapper) {
            return new Value(mapper.apply(this.value));
        }
        public T merge(T other, BinaryOperator<T> op) {
            return this.value = op.apply(this.value, other);
        }
    }
    class Atomic<T> extends AtomicReference<T> implements Box<T> {
        Atomic(T value) {
            super(value);
        }
        public T modify(UnaryOperator<T> op) {
            return updateAndGet(op);
        }
        public <R> Box<R> map(Function<T, R> mapper) {
            return new Atomic(mapper.apply(get()));
        }
        public T merge(T other, BinaryOperator<T> op) {
            return accumulateAndGet(other, op);
        }
        public Box<T> create(T other) {
            return new Atomic(other);
        }
    }
}
