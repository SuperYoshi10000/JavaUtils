package local.ytk.util.function;

import local.ytk.util.Result;
import local.ytk.util.annotation.Static;

import java.util.function.*;

@Static
public class ThrowableFunctions {
    private ThrowableFunctions() {
    }
    
    public static <T extends Throwable> boolean tryRun(ThrowableRunnable<T> throwableRunnable) {
        try {
            throwableRunnable.run();
            return true;
        } catch (Throwable ignore) {
            return false;
        }
    }
    
    public static <T extends Throwable> Runnable nonThrowableOrElse(ThrowableRunnable<T> throwableRunnable, Consumer<? super Throwable> consumer) {
        return () -> {
            try {
                throwableRunnable.run();
            } catch (Throwable e) {
                consumer.accept(e);
            }
        };
    }
    
    public static <T extends Throwable> Runnable nonThrowable(ThrowableRunnable<T> throwableRunnable) {
        return () -> {
            try {
                throwableRunnable.run();
            } catch (Throwable ignored) {
            }
        };
    }
    
    public static <A, T extends Throwable> boolean tryAccept(ThrowableConsumer<A, T> throwableConsumer, A arg) {
        try {
            throwableConsumer.accept(arg);
            return true;
        } catch (Throwable ignore) {
            return false;
        }
    }
    
    public static <A, T extends Throwable> Consumer<A> nonThrowableOrElse(ThrowableConsumer<A, T> throwableConsumer, Consumer<? super Throwable> consumer) {
        return a -> {
            try {
                throwableConsumer.accept(a);
            } catch (Throwable e) {
                consumer.accept(e);
            }
        };
    }
    
    public static <A, T extends Throwable> Consumer<A> nonThrowable(ThrowableConsumer<A, T> throwableConsumer) {
        return a -> {
            try {
                throwableConsumer.accept(a);
            } catch (Throwable ignored) {
            }
        };
    }
    
    public static <A, B, T extends Throwable> boolean tryAccept(ThrowableBiConsumer<A, B, T> throwableBiConsumer, A arg1, B arg2) {
        try {
            throwableBiConsumer.accept(arg1, arg2);
            return true;
        } catch (Throwable ignore) {
            return false;
        }
    }
    
    public static <A, B, T extends Throwable> BiConsumer<A, B> nonThrowableOrElse(ThrowableBiConsumer<A, B, T> throwableBiConsumer, Consumer<? super Throwable> consumer) {
        return (a, b) -> {
            try {
                throwableBiConsumer.accept(a, b);
            } catch (Throwable e) {
                consumer.accept(e);
            }
        };
    }
    
    public static <A, B, T extends Throwable> BiConsumer<A, B> nonThrowable(ThrowableBiConsumer<A, B, T> throwableBiConsumer) {
        return (a, b) -> {
            try {
                throwableBiConsumer.accept(a, b);
            } catch (Throwable ignored) {
            }
        };
    }
    
    public static <R, T extends Throwable> Result<R> tryGet(ThrowableSupplier<R, T> throwableSupplier) {
        try {
            return Result.of(throwableSupplier.get());
        } catch (Throwable ignore) {
            return Result.fail();
        }
    }
    
    public static <R, T extends Throwable> Supplier<R> nonThrowableOrElse(ThrowableSupplier<R, T> throwableSupplier, Function<? super Throwable, R> function) {
        return () -> {
            try {
                return throwableSupplier.get();
            } catch (Throwable e) {
                return function.apply(e);
            }
        };
    }
    
    public static <R, T extends Throwable> Supplier<R> nonThrowable(ThrowableSupplier<R, T> throwableSupplier) {
        return throwableSupplier.nonThrowable((R) null);
    }
    
    public static <R, T extends Throwable> Supplier<R> nonThrowable(ThrowableSupplier<R, T> throwableSupplier, R defaultValue) {
        return () -> {
            try {
                return throwableSupplier.get();
            } catch (Throwable ignored) {
                return defaultValue;
            }
        };
    }
    
    public static <A, R, T extends Throwable> Result<R> tryApply(ThrowableFunction<A, R, T> throwableFunction, A arg) {
        try {
            return Result.of(throwableFunction.apply(arg));
        } catch (Throwable ignore) {
            return Result.fail();
        }
    }
    
    public static <A, R, T extends Throwable> Function<A, R> nonThrowableOrElse(ThrowableFunction<A, R, T> throwableFunction, Function<? super Throwable, R> function) {
        return a -> {
            try {
                return throwableFunction.apply(a);
            } catch (Throwable e) {
                return function.apply(e);
            }
        };
    }
    
    public static <A, R, T extends Throwable> Function<A, R> nonThrowable(ThrowableFunction<A, R, T> throwableFunction) {
        return throwableFunction.nonThrowable((R) null);
    }
    
    public static <A, R, T extends Throwable> Function<A, R> nonThrowable(ThrowableFunction<A, R, T> throwableFunction, R defaultValue) {
        return a -> {
            try {
                return throwableFunction.apply(a);
            } catch (Throwable ignored) {
                return defaultValue;
            }
        };
    }
    
    public static <A, B, R, T extends Throwable> Result<R> tryApply(ThrowableBiFunction<A, B, R, T> throwableBiFunction, A arg1, B arg2) {
        try {
            return Result.of(throwableBiFunction.apply(arg1, arg2));
        } catch (Throwable ignore) {
            return Result.fail();
        }
    }
    
    public static <A, B, R, T extends Throwable> BiFunction<A, B, R> nonThrowableOrElse(ThrowableBiFunction<A, B, R, T> throwableBiFunction, Function<? super Throwable, R> function) {
        return (a, b) -> {
            try {
                return throwableBiFunction.apply(a, b);
            } catch (Throwable e) {
                return function.apply(e);
            }
        };
    }
    
    public static <A, B, R, T extends Throwable> BiFunction<A, B, R> nonThrowable(ThrowableBiFunction<A, B, R, T> throwableBiFunction) {
        return throwableBiFunction.nonThrowable((R) null);
    }
    
    public static <A, B, R, T extends Throwable> BiFunction<A, B, R> nonThrowable(ThrowableBiFunction<A, B, R, T> throwableBiFunction, R defaultValue) {
        return (a, b) -> {
            try {
                return throwableBiFunction.apply(a, b);
            } catch (Throwable ignored) {
                return defaultValue;
            }
        };
    }
    
    public interface ThrowableRunnable<T extends Throwable> {
        void run() throws T;
        
        default boolean tryRun() {
            return ThrowableFunctions.tryRun(this);
        }
        
        default Runnable nonThrowableOrElse(Consumer<? super Throwable> consumer) {
            return ThrowableFunctions.nonThrowableOrElse(this, consumer);
        }
        
        default Runnable nonThrowable() {
            return ThrowableFunctions.nonThrowable(this);
        }
    }
    
    public interface ThrowableConsumer<A, T extends Throwable> {
        void accept(A arg) throws T;
        
        default boolean tryAccept(A arg) {
            return ThrowableFunctions.tryAccept(this, arg);
        }
        
        default Consumer<A> nonThrowableOrElse(Consumer<? super Throwable> consumer) {
            return ThrowableFunctions.nonThrowableOrElse(this, consumer);
        }
        
        default Consumer<A> nonThrowable() {
            return ThrowableFunctions.nonThrowable(this);
        }
    }
    
    public interface ThrowableBiConsumer<A, B, T extends Throwable> {
        void accept(A arg1, B arg2) throws T;
        
        default boolean tryAccept(A arg1, B arg2) {
            return ThrowableFunctions.tryAccept(this, arg1, arg2);
        }
        
        default BiConsumer<A, B> nonThrowableOrElse(Consumer<? super Throwable> consumer) {
            return ThrowableFunctions.nonThrowableOrElse(this, consumer);
        }
        
        default BiConsumer<A, B> nonThrowable() {
            return ThrowableFunctions.nonThrowable(this);
        }
    }
    
    ////////
    public interface ThrowableSupplier<R, T extends Throwable> {
        R get() throws T;
        
        default Result<R> tryGet() {
            return ThrowableFunctions.tryGet(this);
        }
        
        default Supplier<R> nonThrowableOrElse(Function<? super Throwable, R> function) {
            return ThrowableFunctions.nonThrowableOrElse(this, function);
        }
        
        default Supplier<R> nonThrowable() {
            return ThrowableFunctions.nonThrowable(this);
        }
        
        default Supplier<R> nonThrowable(R defaultValue) {
            return ThrowableFunctions.nonThrowable(this, defaultValue);
        }
    }
    
    public interface ThrowableFunction<A, R, T extends Throwable> {
        R apply(A arg) throws T;
        
        default Result<R> tryApply(A arg) {
            return ThrowableFunctions.tryApply(this, arg);
        }
        
        default Function<A, R> nonThrowableOrElse(Function<? super Throwable, R> function) {
            return ThrowableFunctions.nonThrowableOrElse(this, function);
        }
        
        default Function<A, R> nonThrowable() {
            return ThrowableFunctions.nonThrowable(this);
        }
        
        default Function<A, R> nonThrowable(R defaultValue) {
            return ThrowableFunctions.nonThrowable(this, defaultValue);
        }
    }
    
    public interface ThrowableBiFunction<A, B, R, T extends Throwable> {
        R apply(A arg1, B arg2) throws T;
        
        default Result<R> tryApply(A arg1, B arg2) {
            return ThrowableFunctions.tryApply(this, arg1, arg2);
        }
        
        default BiFunction<A, B, R> nonThrowableOrElse(Function<? super Throwable, R> function) {
            return ThrowableFunctions.nonThrowableOrElse(this, function);
        }
        
        default BiFunction<A, B, R> nonThrowable() {
            return ThrowableFunctions.nonThrowable(this);
        }
        
        default BiFunction<A, B, R> nonThrowable(R defaultValue) {
            return ThrowableFunctions.nonThrowable(this, defaultValue);
        }
    }
}
