package local.ytk.util.reflect;

import local.ytk.util.ObjectBuilder;
import local.ytk.util.function.FunctionUtils;
import local.ytk.util.function.ItemSelector;
import local.ytk.util.function.ThrowableFunctions;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Accessors {
    private Accessors() {}
    
    public static <O, F> Function<O, F> get(Field f) {
        Class<O> cls = (Class<O>) f.getDeclaringClass();
        return FunctionUtils.cast(ThrowableFunctions.nonThrowable(f::get));
    }
    public static <O, F> BiFunction<O, F, F> set(Field f) {
        Class<O> cls = (Class<O>) f.getDeclaringClass();
        return FunctionUtils.returnArg2(ThrowableFunctions.nonThrowable(f::set));
    }
    
    public static <O, F> Function<O, F> get(Class<O> cls, Field f) {
        return FunctionUtils.cast(ThrowableFunctions.nonThrowable(f::get));
    }
    public static <O, F> BiFunction<O, F, F> set(Class<O> cls, Field f) {
        return FunctionUtils.returnArg2(ThrowableFunctions.nonThrowable(f::set));
    }
    
    public static <T> ThrowableFunctions.ThrowableSupplier<T, ?> constructor(Class<T> cls) {
        try {
            return cls.getConstructor()::newInstance;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public static <T> T newInstance(Class<T> cls) {
        return newInstance(cls, ObjectBuilder.NO_ARGS);
    }
    public static <T> T newInstance(Class<T> cls, ItemSelector<Constructor<?>> selector) {
        try {
            @SuppressWarnings("unchecked")
            Constructor<T> constructor = (Constructor<T>) selector.apply(cls.getConstructors());
            return constructor.newInstance(Stream.of(constructor.getParameterTypes()).map(t ->
                t.isPrimitive() ?
                    t == boolean.class ? false :
                    t == byte.class ? (byte) 0 :
                    t == short.class ? (short) 0 :
                    t == int.class ? 0 :
                    t == long.class ? 0L :
                    t == float.class ? 0f :
                    t == double.class ? 0d :
                    t == char.class ? '\0' :
                    ObjectBuilder.NULL_PLACEHOLDER
                : newInstance(t)).toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | ClassCastException e) {
            return null;
        }
    }
    
    public static int getArrayLevel(Class<?> cls) {
        int l = 0;
        while (cls.isArray()) {
            cls = cls.componentType();
            l++;
        }
        return l;
    }
    public static Class<?> getArrayComponentType(Class<?> cls) {
        while (cls.isArray()) cls = cls.componentType();
        return cls;
    }
    
    public static String betterClassName(Object obj) {
        return betterClassName(obj.getClass());
    }
    public static String betterClassName(Class<?> cls) {
        if (cls.isArray()) return betterClassName(cls.componentType()) + "[]";
        if (cls.isPrimitive()) return cls.getCanonicalName();
        if (cls.isLocalClass()) return "(local) " + cls.getName();
        
        if (cls.isAnonymousClass()) {
            Class<?> c = cls.getEnclosingClass();
            String n = betterClassName(c);
            Class<?> s = cls.getSuperclass();
            Class<?> a = (s == Object.class && cls.getInterfaces().length > 0) ? cls.getInterfaces()[0] : s;
            return "(anonymous) %s of %s in %s".formatted(cls.getName(), betterClassName(a), c.isAnonymousClass() ? "(" + n + ")" : n);
        }
//        if (cls.getName()) return "(unnamed)";
        if (cls.isHidden()) return "(hidden) " + cls.getName();
        return cls.getName();
    }
}
