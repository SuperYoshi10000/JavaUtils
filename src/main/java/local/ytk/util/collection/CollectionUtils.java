package local.ytk.util.collection;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import local.ytk.util.annotation.Static;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Static
public class CollectionUtils {
    public static IntArrayList indexes(List<?> list) {
        return range(list.size());
    }
    @SuppressWarnings("deprecation")
    public static <T> Int2ObjectOpenHashMap<T> toIntMap(List<T> list) {
        return new Int2ObjectOpenHashMap<T>(indexes(list).stream().collect(Collectors.toMap(i -> i, list::get)));
    }
    
    public static <T> Collector<T, ?, T[]> collectToArray(IntFunction<T[]> generator) {
        return Collector.of(ArrayList::new, List::add, (a, b) -> { a.addAll(b); return a; }, a -> a.toArray(generator));
    }
    
    public static <T, A, R1, R2> Collector<T, A, R2> map(Collector<T, A, R1> collector, Function<R1, R2> mapper) {
        return Collector.of(
                collector.supplier(),
                collector.accumulator(),
                collector.combiner(),
                collector.finisher().andThen(mapper),
                collector.characteristics().toArray(Collector.Characteristics[]::new)
        );
    }
    
    public static IntArrayList range(int max) {
        return range(0, max, 1);
    }
    public static IntArrayList range(int min, int max) {
        return range(min, max, 1);
    }
    public static IntArrayList range(int min, int max, int count) {
        IntArrayList intList = new IntArrayList();
        for (int i = min; i < max; i += count) {
            intList.add(i);
        }
        return intList;
    }
    
    public static <T> void forEachIf(Collection<T> collection, Predicate<T> condition, Consumer<T> action) {
        for (T i : collection) if (condition.test(i)) action.accept(i);
    }
    
    public static <T> T matchFirst(Collection<T> collection, Predicate<T> condition) {
        for (T i : collection) if (condition.test(i)) return i;
        return null;
    }
    public static <T, R> R matchFirst(Collection<T> collection, Predicate<T> condition, Function<T, R> mapper) {
        for (T i : collection) if (condition.test(i)) return mapper.apply(i);
        return null;
    }
    public static <T> T matchLast(SequencedCollection<T> collection, Predicate<T> condition) {
        for (T i : collection.reversed()) if (condition.test(i)) return i;
        return null;
    }
    public static <T, R> R matchLast(SequencedCollection<T> collection, Predicate<T> condition, Function<T, R> mapper) {
        for (T i : collection.reversed()) if (condition.test(i)) return mapper.apply(i);
        return null;
    }
    
    public static <K, V> Map<K, V> asMapValues(Collection<V> collection, Function<V, K> mapper) {
        Map<K, V> map = new HashMap<>();
        collection.forEach(i -> map.put(mapper.apply(i), i));
        return map;
    }
    public static <K, V> Map<K, V> asMapKeys(Collection<K> collection, Function<K, V> mapper) {
        Map<K, V> map = new HashMap<>();
        collection.forEach(i -> map.put(i, mapper.apply(i)));
        return map;
    }
}
