package local.ytk.util.function;

import local.ytk.util.Result;
import local.ytk.util.annotation.Static;

import java.util.function.*;

@Static
public class ThrowingFunctions {
    private ThrowingFunctions() {
    }
    
    public static <T extends Throwable> boolean tryRun(ThrowingRunnable<T> throwingRunnable) {
        try {
            throwingRunnable.run();
            return true;
        } catch (Throwable ignore) {
            return false;
        }
    }
    
    public static <T extends Throwable> Runnable nonThrowingOrElse(ThrowingRunnable<T> throwingRunnable, Consumer<? super Throwable> consumer) {
        return () -> {
            try {
                throwingRunnable.run();
            } catch (Throwable e) {
                consumer.accept(e);
            }
        };
    }
    
    public static <T extends Throwable> Runnable nonThrowing(ThrowingRunnable<T> throwingRunnable) {
        return () -> {
            try {
                throwingRunnable.run();
            } catch (Throwable ignored) {
            }
        };
    }
    
    public static <A, T extends Throwable> boolean tryAccept(ThrowingConsumer<A, T> throwingConsumer, A arg) {
        try {
            throwingConsumer.accept(arg);
            return true;
        } catch (Throwable ignore) {
            return false;
        }
    }
    
    public static <A, T extends Throwable> Consumer<A> nonThrowingOrElse(ThrowingConsumer<A, T> throwingConsumer, Consumer<? super Throwable> consumer) {
        return a -> {
            try {
                throwingConsumer.accept(a);
            } catch (Throwable e) {
                consumer.accept(e);
            }
        };
    }
    
    public static <A, T extends Throwable> Consumer<A> nonThrowing(ThrowingConsumer<A, T> throwingConsumer) {
        return a -> {
            try {
                throwingConsumer.accept(a);
            } catch (Throwable ignored) {
            }
        };
    }
    
    public static <A, B, T extends Throwable> boolean tryAccept(ThrowingBiConsumer<A, B, T> throwingBiConsumer, A arg1, B arg2) {
        try {
            throwingBiConsumer.accept(arg1, arg2);
            return true;
        } catch (Throwable ignore) {
            return false;
        }
    }
    
    public static <A, B, T extends Throwable> BiConsumer<A, B> nonThrowingOrElse(ThrowingBiConsumer<A, B, T> throwingBiConsumer, Consumer<? super Throwable> consumer) {
        return (a, b) -> {
            try {
                throwingBiConsumer.accept(a, b);
            } catch (Throwable e) {
                consumer.accept(e);
            }
        };
    }
    
    public static <A, B, T extends Throwable> BiConsumer<A, B> nonThrowing(ThrowingBiConsumer<A, B, T> throwingBiConsumer) {
        return (a, b) -> {
            try {
                throwingBiConsumer.accept(a, b);
            } catch (Throwable ignored) {
            }
        };
    }
    
    public static <R, T extends Throwable> Result<R> tryGet(ThrowingSupplier<R, T> throwingSupplier) {
        try {
            return Result.of(throwingSupplier.get());
        } catch (Throwable ignore) {
            return Result.fail();
        }
    }
    
    public static <R, T extends Throwable> Supplier<R> nonThrowingOrElse(ThrowingSupplier<R, T> throwingSupplier, Function<? super Throwable, R> function) {
        return () -> {
            try {
                return throwingSupplier.get();
            } catch (Throwable e) {
                return function.apply(e);
            }
        };
    }
    
    public static <R, T extends Throwable> Supplier<R> nonThrowing(ThrowingSupplier<R, T> throwingSupplier) {
        return throwingSupplier.nonThrowing((R) null);
    }
    
    public static <R, T extends Throwable> Supplier<R> nonThrowing(ThrowingSupplier<R, T> throwingSupplier, R defaultValue) {
        return () -> {
            try {
                return throwingSupplier.get();
            } catch (Throwable ignored) {
                return defaultValue;
            }
        };
    }
    
    public static <A, R, T extends Throwable> Result<R> tryApply(ThrowingFunction<A, R, T> throwingFunction, A arg) {
        try {
            return Result.of(throwingFunction.apply(arg));
        } catch (Throwable ignore) {
            return Result.fail();
        }
    }
    
    public static <A, R, T extends Throwable> Function<A, R> nonThrowingOrElse(ThrowingFunction<A, R, T> throwingFunction, Function<? super Throwable, R> function) {
        return a -> {
            try {
                return throwingFunction.apply(a);
            } catch (Throwable e) {
                return function.apply(e);
            }
        };
    }
    
    public static <A, R, T extends Throwable> Function<A, R> nonThrowing(ThrowingFunction<A, R, T> throwingFunction) {
        return throwingFunction.nonThrowing((R) null);
    }
    
    public static <A, R, T extends Throwable> Function<A, R> nonThrowing(ThrowingFunction<A, R, T> throwingFunction, R defaultValue) {
        return a -> {
            try {
                return throwingFunction.apply(a);
            } catch (Throwable ignored) {
                return defaultValue;
            }
        };
    }
    
    public static <A, B, R, T extends Throwable> Result<R> tryApply(ThrowingBiFunction<A, B, R, T> throwingBiFunction, A arg1, B arg2) {
        try {
            return Result.of(throwingBiFunction.apply(arg1, arg2));
        } catch (Throwable ignore) {
            return Result.fail();
        }
    }
    
    public static <A, B, R, T extends Throwable> BiFunction<A, B, R> nonThrowingOrElse(ThrowingBiFunction<A, B, R, T> throwingBiFunction, Function<? super Throwable, R> function) {
        return (a, b) -> {
            try {
                return throwingBiFunction.apply(a, b);
            } catch (Throwable e) {
                return function.apply(e);
            }
        };
    }
    
    public static <A, B, R, T extends Throwable> BiFunction<A, B, R> nonThrowing(ThrowingBiFunction<A, B, R, T> throwingBiFunction) {
        return throwingBiFunction.nonThrowing((R) null);
    }
    
    public static <A, B, R, T extends Throwable> BiFunction<A, B, R> nonThrowing(ThrowingBiFunction<A, B, R, T> throwingBiFunction, R defaultValue) {
        return (a, b) -> {
            try {
                return throwingBiFunction.apply(a, b);
            } catch (Throwable ignored) {
                return defaultValue;
            }
        };
    }
    
    public interface Throwing<F, T extends Throwable> {
        F nonThrowing();
        F nonThrowingOrElse(Consumer<? super T> consumer);
    }
    public interface ThrowingWithOutput<F, R, T extends Throwable> extends Throwing<F, T> {
        R nonThrowingOrElse(R result);
        F nonThrowingOrElse(Function<? super T, R> function);
    }

    public interface ThrowingRunnable<T extends Throwable> {
        void run() throws T;
        
        default boolean tryRun() {
            return ThrowingFunctions.tryRun(this);
        }
        
        default Runnable nonThrowingOrElse(Consumer<? super Throwable> consumer) {
            return ThrowingFunctions.nonThrowingOrElse(this, consumer);
        }
        
        default Runnable nonThrowing() {
            return ThrowingFunctions.nonThrowing(this);
        }
    }
    
    public interface ThrowingConsumer<A, T extends Throwable> {
        void accept(A arg) throws T;
        
        default boolean tryAccept(A arg) {
            return ThrowingFunctions.tryAccept(this, arg);
        }
        
        default Consumer<A> nonThrowingOrElse(Consumer<? super Throwable> consumer) {
            return ThrowingFunctions.nonThrowingOrElse(this, consumer);
        }
        
        default Consumer<A> nonThrowing() {
            return ThrowingFunctions.nonThrowing(this);
        }
    }
    
    public interface ThrowingBiConsumer<A, B, T extends Throwable> {
        void accept(A arg1, B arg2) throws T;
        
        default boolean tryAccept(A arg1, B arg2) {
            return ThrowingFunctions.tryAccept(this, arg1, arg2);
        }
        
        default BiConsumer<A, B> nonThrowingOrElse(Consumer<? super Throwable> consumer) {
            return ThrowingFunctions.nonThrowingOrElse(this, consumer);
        }
        
        default BiConsumer<A, B> nonThrowing() {
            return ThrowingFunctions.nonThrowing(this);
        }
    }
    
    ////////
    public interface ThrowingSupplier<R, T extends Throwable> {
        R get() throws T;
        
        default Result<R> tryGet() {
            return ThrowingFunctions.tryGet(this);
        }
        
        default Supplier<R> nonThrowingOrElse(Function<? super Throwable, R> function) {
            return ThrowingFunctions.nonThrowingOrElse(this, function);
        }
        
        default Supplier<R> nonThrowing() {
            return ThrowingFunctions.nonThrowing(this);
        }
        
        default Supplier<R> nonThrowing(R defaultValue) {
            return ThrowingFunctions.nonThrowing(this, defaultValue);
        }
    }
    
    public interface ThrowingFunction<A, R, T extends Throwable> {
        R apply(A arg) throws T;
        
        default Result<R> tryApply(A arg) {
            return ThrowingFunctions.tryApply(this, arg);
        }
        
        default Function<A, R> nonThrowingOrElse(Function<? super Throwable, R> function) {
            return ThrowingFunctions.nonThrowingOrElse(this, function);
        }
        
        default Function<A, R> nonThrowing() {
            return ThrowingFunctions.nonThrowing(this);
        }
        
        default Function<A, R> nonThrowing(R defaultValue) {
            return ThrowingFunctions.nonThrowing(this, defaultValue);
        }
    }
    
    public interface ThrowingBiFunction<A, B, R, T extends Throwable> {
        R apply(A arg1, B arg2) throws T;
        
        default Result<R> tryApply(A arg1, B arg2) {
            return ThrowingFunctions.tryApply(this, arg1, arg2);
        }
        
        default BiFunction<A, B, R> nonThrowingOrElse(Function<? super Throwable, R> function) {
            return ThrowingFunctions.nonThrowingOrElse(this, function);
        }
        
        default BiFunction<A, B, R> nonThrowing() {
            return ThrowingFunctions.nonThrowing(this);
        }
        
        default BiFunction<A, B, R> nonThrowing(R defaultValue) {
            return ThrowingFunctions.nonThrowing(this, defaultValue);
        }
    }
}
