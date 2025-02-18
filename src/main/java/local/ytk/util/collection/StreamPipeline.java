package local.ytk.util.collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.*;

public abstract class StreamPipeline<I, T> implements Stream<T>, Mapper<Stream<? extends I>, Stream<? extends T>> {
    public static <T> StreamPipeline<T, T> ofEmpty() {
        return new StreamPipeline<>() {
            @Override
            public Stream<? extends T> apply(Stream<? extends T> t) {
                return t;
            }
        };
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Stream<T> nonGenericStream(Stream<? extends T> stream) {
        return (Stream<T>) stream;
    }
    
    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
        return new FilterStream(predicate);
    }
    
    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
        return new MapStream<>(mapper);
    }
    
    @Nullable
    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
        return null;
    }
    
    @Nullable
    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
        return null;
    }
    
    @Nullable
    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
        return null;
    }
    
    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return new FlatMapStream<>(mapper);
    }
    
    @Nullable
    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
        return null;
    }
    
    @Nullable
    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
        return null;
    }
    
    @Nullable
    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
        return null;
    }
    
    @Override
    public Stream<T> distinct() {
        return new StreamMapStream<>(Stream::distinct);
    }
    
    @Override
    public Stream<T> sorted() {
        return new StreamMapStream<>(Stream::sorted);
    }
    
    @Override
    public Stream<T> sorted(Comparator<? super T> comparator) {
        return new StreamMapStream<>(s -> s.sorted(comparator));
    }
    
    @Override
    public Stream<T> peek(Consumer<? super T> action) {
        return new ActionStream(action);
    }
    
    @Override
    public Stream<T> limit(long maxSize) {
        return new StreamMapStream<>(s -> s.limit(maxSize));
    }
    
    @Override
    public Stream<T> skip(long n) {
        return new StreamMapStream<>(s -> s.skip(n));
    }
    
    @Override
    public void forEach(Consumer<? super T> action) {
        new ActionStream(action);
    }
    
    @Override
    public void forEachOrdered(Consumer<? super T> action) {
        new ActionStream(action);
    }
    
    @NotNull
    @Override
    public Object[] toArray() {
        return new Object[0];
        // throw new UnsupportedOperationException("StreamPipeline cannot contain any items");
    }
    
    @NotNull
    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
        return generator.apply(0);
        // throw new UnsupportedOperationException("StreamPipeline cannot contain any items");
    }
    
    @Nullable
    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        return null;
    }
    
    @NotNull
    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        return Optional.empty();
    }
    
    @Nullable
    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        return null;
    }
    
    @Nullable
    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        return null;
    }
    
    @Nullable
    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return null;
    }
    
    @NotNull
    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        return Optional.empty();
    }
    
    @NotNull
    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        return Optional.empty();
    }
    
    @Override
    public long count() {
        return 0;
    }
    
    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return false;
    }
    
    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return true;
    }
    
    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
        return true;
    }
    
    @NotNull
    @Override
    public Optional<T> findFirst() {
        return Optional.empty();
    }
    
    @NotNull
    @Override
    public Optional<T> findAny() {
        return Optional.empty();
    }
    
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return false;
            }
            
            @Nullable
            @Override
            public T next() {
                return null;
            }
        };
    }
    
    @NotNull
    @Override
    public Spliterator<T> spliterator() {
        return new Spliterator<>() {
            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                return false;
            }
            
            @Nullable
            @Override
            public Spliterator<T> trySplit() {
                return null;
            }
            
            @Override
            public long estimateSize() {
                return 0;
            }
            
            @Override
            public int characteristics() {
                return 0;
            }
        };
    }
    
    @Override
    public boolean isParallel() {
        return false;
    }
    
    @NotNull
    @Override
    public Stream<T> sequential() {
        return this;
    }
    
    @NotNull
    @Override
    public Stream<T> parallel() {
        return this;
    }
    
    @NotNull
    @Override
    public Stream<T> unordered() {
        return this;
    }
    
    @NotNull
    @Override
    public Stream<T> onClose(Runnable closeHandler) {
        return new StreamPipeline<>() {
            @Override
            public Stream<? extends T> apply(Stream<?> i) {
                closeHandler.run();
                return StreamPipeline.this.apply((Stream<? extends I>) i);
            }
            
            @Override
            public void close() {
                super.close();
                closeHandler.run();
            }
        };
    }
    
    @Override
    public void close() {}
    public abstract Stream<? extends T> apply(Stream<? extends I> i);
    
    public class MapStream<U> extends StreamPipeline<I, U> {
        protected final Function<? super T, ? extends U> mapper;
        public MapStream(Function<? super T, ? extends U> mapper) {
            this.mapper = mapper;
        }
        
        @Override
        public Stream<? extends U> apply(Stream<? extends I> i) {
            return StreamPipeline.this.apply(i).map(mapper);
        }
    }
    public class FlatMapStream<U> extends StreamPipeline<I, U> {
        protected final Function<? super T, ? extends Stream<? extends U>> mapper;
        public FlatMapStream(Function<? super T, ? extends Stream<? extends U>> mapper) {
            this.mapper = mapper;
        }
        
        @Override
        public Stream<? extends U> apply(Stream<? extends I> i) {
            return StreamPipeline.this.apply(i).flatMap(mapper);
        }
    }
    public class StreamMapStream<U> extends StreamPipeline<I, U> {
        protected final Function<? super Stream<? extends T>, ? extends Stream<? extends U>> mapper;
        public StreamMapStream(Function<? super Stream<? extends T>, ? extends Stream<? extends U>> mapper) {
            this.mapper = mapper;
        }
        
        @Override
        public Stream<? extends U> apply(Stream<? extends I> i) {
            return mapper.apply(StreamPipeline.this.apply(i));
        }
    }
    
    public class FilterStream extends StreamPipeline<I, T> {
        protected final Predicate<? super T> filter;
        public FilterStream(Predicate<? super T> filter) {
            this.filter = filter;
        }
        
        @Override
        public Stream<? extends T> apply(Stream<? extends I> i) {
            return StreamPipeline.this.apply(i);
        }
    }
    
    public class ActionStream extends StreamPipeline<I, T> {
        protected final Consumer<? super T> action;
        public ActionStream(Consumer<? super T> action) {
            this.action = action;
        }
        
        @Override
        public Stream<? extends T> apply(Stream<? extends I> i) {
            return StreamPipeline.this.apply(i).peek(action);
        }
    }
}
