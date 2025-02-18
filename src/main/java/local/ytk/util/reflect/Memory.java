package local.ytk.util.reflect;

//import org.jetbrains.annotations.ApiStatus;

/*
import sun.misc.Unsafe;

import java.lang.foreign.Arena;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

//@ApiStatus.Experimental
public class Memory {
    private Memory() {}
    
    private static final Arena ARENA = Arena.global();
    private static final Unsafe UNSAFE = Unsafe.getUnsafe();
    
    public static native AtomicLong addressOf(Object obj);
    public static native AtomicLong addressOf(Field f);
    
    public static native AtomicLong addressOf(Object obj, Field f);
    public static native AtomicLong addressOf(Method m);
    public static native AtomicLong addressOf(Object obj, Method m);
    public static native AtomicLong addressOf(Constructor<?> c);
    public static native AtomicLong addressOf(Class<?> cls);
    public static native AtomicLong addressOf(Object obj, Class<?> cls);
    
    public static AtomicLong addressOfObj(Object obj) {
        return addressOf(obj);
    }
    
    public static native VarHandle varHandle(long address);
    public static native WeakReference<?> weakReference(long address);
    public static native AtomicReference<?> reference(long address);
    public static native byte[] bytesAt(long address, int length);
    public static ByteBuffer byteBufferAt(long address, int length) {
        return ByteBuffer.wrap(bytesAt(address, length));
    }
    
    public static ValueLayout valueLayout(Class<?> cls) {
        return switch (cls.getName()) {
            case "boolean" -> ValueLayout.JAVA_BOOLEAN;
            case "byte" -> ValueLayout.JAVA_BYTE;
            case "char" -> ValueLayout.JAVA_CHAR;
            case "double" -> ValueLayout.JAVA_DOUBLE;
            case "float" -> ValueLayout.JAVA_FLOAT;
            case "int" -> ValueLayout.JAVA_INT;
            case "long" -> ValueLayout.JAVA_LONG;
            case "short" -> ValueLayout.JAVA_SHORT;
            default -> ValueLayout.ADDRESS;
        };
    }
    public static ValueLayout valueLayoutUnaligned(Class<?> cls) {
        return switch (cls.getName()) {
            case "boolean" -> ValueLayout.JAVA_BOOLEAN;
            case "byte" -> ValueLayout.JAVA_BYTE;
            case "char" -> ValueLayout.JAVA_CHAR_UNALIGNED;
            case "double" -> ValueLayout.JAVA_DOUBLE_UNALIGNED;
            case "float" -> ValueLayout.JAVA_FLOAT_UNALIGNED;
            case "int" -> ValueLayout.JAVA_INT_UNALIGNED;
            case "long" -> ValueLayout.JAVA_LONG_UNALIGNED;
            case "short" -> ValueLayout.JAVA_SHORT_UNALIGNED;
            default -> ValueLayout.ADDRESS_UNALIGNED;
        };
    }
    public static MemoryLayout layoutOf(Object obj) {
        List<Field> fields = new ArrayList<>();
        Class<?> cls = obj.getClass();
        while (cls != null) {
            fields.addAll(List.of(cls.getFields()));
            cls = cls.getSuperclass();
        }
        fields.removeIf(f -> Modifier.isStatic(f.getModifiers()));
        List<ValueLayout> list = new ArrayList<>();
        long offset = 0;
        for (Field f : fields) {
            Class<?> type = f.getType();
            ValueLayout oldValueLayout = valueLayout(type);
            ValueLayout newValueLayout = oldValueLayout
                    .withByteAlignment(offset % oldValueLayout.byteSize())
                    .withName(f.getName());
            list.add(newValueLayout);
            offset += newValueLayout.byteSize();
        }
        MemoryLayout[] fieldLayout = list.toArray(new MemoryLayout[0]);
        return MemoryLayout.structLayout(fieldLayout);
    }
    
    public static MemorySegment segment(Object obj) {
        return MemorySegment.ofAddress(addressOfObj(obj).get());
    }
    
    public static void main(String[] args) {
    
    }
}
*/