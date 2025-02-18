package local.ytk.util;

import local.ytk.util.annotation.Static;
import local.ytk.util.function.ItemSelector;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Static
public class ObjectBuilder {
    public static final Object NULL_PLACEHOLDER = placeholder();
    public static final Constructor<Object> OBJECT_CONSTRUCTOR;
    public static final ItemSelector<Constructor<?>> NO_ARGS;
    
    static {
        try {
            OBJECT_CONSTRUCTOR = Object.class.getConstructor();
            NO_ARGS = c -> Arrays.stream(c).min(Comparator.comparingInt(Constructor::getParameterCount)).orElse(OBJECT_CONSTRUCTOR);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T> T build(Supplier<T> builder){
        return builder.get();
    }
    public static <T> T build(T value, Consumer<T> builder){
        builder.accept(value);
        return value;
    }
    public static <T, U> U build(T value, Function<T, U> builder){
        return builder.apply(value);
    }
    
    public static Object placeholder() {
        return new Symbol();
    }
}
