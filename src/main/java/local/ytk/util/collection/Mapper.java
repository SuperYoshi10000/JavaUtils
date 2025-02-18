package local.ytk.util.collection;

import local.ytk.util.function.FunctionUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Mapper<T, R> extends Function<T, R> {
    static <T, R> Mapper<T, R> ofFunction(Function<T, R> function) {
        return function::apply;
    }
    
    default R map(T arg) {
        return apply(arg);
    }
    
    default Object[] map(T... arg) {
        return map(Object[]::new, arg);
    }
    default <O> O[] map(Class<O> type, T... arg) {
        return map(n -> (O[]) Array.newInstance(type, n), arg);
    }
    
    default <O> O[] map(IntFunction<O[]> generator, T... arg) {
        return map(generator.apply(arg.length), arg);
        
    }
    default <O> O[] map(O[] newArray, T... arg) {
        try {
            if (newArray.length < arg.length) newArray = (O[]) Array.newInstance(newArray.getClass(), arg.length);
            if (newArray.length > arg.length) newArray[arg.length] = null;
            for (int i = 0; i < arg.length; i++) {
                newArray[i] = (O) map(arg[i]);
            }
        } catch (ClassCastException ignore) {}
        return newArray;
    }
    
    default List<R> map(Collection<? extends T> arg) {
        return arg.stream().map(this).toList();
    }
    default <C> C map(Collection<? extends T> arg, Function<? super List<? super R>, ? extends C> constructor) {
        return constructor.apply(map(arg));
    }
    default <C extends Collection<R>> C map(Collection<? extends T> arg, Supplier<C> constructor) {
        C collection = constructor.get();
        collection.addAll(map(arg));
        return collection;
    }
    
    default <C> C map(Collection<? extends T> arg, Supplier<C> constructor, BiConsumer<C, ? super R> accumulator, BiConsumer<C, C> combiner) {
        return arg.stream().map(this).collect(constructor, accumulator, combiner);
    }
    default <C> C map(Collection<? extends T> arg, Collector<? super R, ?, ? extends C> collector) {
        return arg.stream().map(this).collect(collector);
    }
    
    
    static <K1, K2, V1, V2> Map<K2, V2> mapMap(Map<K1, V1> map, Function<K1, K2> keyMapper, Function<V1, V2> valueMapper) {
        return mapMap(map, keyMapper, valueMapper, HashMap::new);
    }
    
    static <K1, K2, V1, V2, M extends Map<K2, V2>> M mapMap(Map<K1, V1> map, Function<K1, K2> keyMapper, Function<V1, V2> valueMapper, Supplier<M> sup) {
        return map
                .entrySet()
                .stream()
                .map(e -> Entries.map(e, keyMapper, valueMapper))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        FunctionUtils.getArg2Op(),
                        sup
                ));
    }
    
    static <K1, K2, V1, V2, M extends Map<K2, V2>> M mapMapSized(Map<K1, V1> map, Function<K1, K2> keyMapper, Function<V1, V2> valueMapper, IntFunction<M> generator) {
        return map
                .entrySet()
                .stream()
                .map(e -> Entries.map(e, keyMapper, valueMapper))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        FunctionUtils.getArg2Op(),
                        () -> generator.apply(map.size())
                ));
    }
    
    static <T> boolean mapEquals(T obj1, T obj2, Function<? super T, ?> mapper) {
        return Objects.equals(mapper.apply(obj1), mapper.apply(obj2));
    }
    static <T> Predicate<T> mapEqualsP(T compare, Function<? super T, ?> mapper) {
        return value -> Objects.equals(mapper.apply(value), mapper.apply(compare));
    }
}
