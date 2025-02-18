package local.ytk.util.math;

import it.unimi.dsi.fastutil.booleans.BooleanBinaryOperator;
import it.unimi.dsi.fastutil.booleans.BooleanPredicate;
import it.unimi.dsi.fastutil.bytes.ByteBinaryOperator;
import it.unimi.dsi.fastutil.bytes.BytePredicate;
import it.unimi.dsi.fastutil.chars.CharBinaryOperator;
import it.unimi.dsi.fastutil.chars.CharPredicate;
import it.unimi.dsi.fastutil.floats.FloatBinaryOperator;
import it.unimi.dsi.fastutil.floats.FloatPredicate;
import it.unimi.dsi.fastutil.ints.*;
import it.unimi.dsi.fastutil.shorts.ShortBinaryOperator;
import it.unimi.dsi.fastutil.shorts.ShortPredicate;
import local.ytk.util.annotation.Static;

import java.util.function.*;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;

@Static
public final class ArrayRange {
    private ArrayRange() {}
    
    public static byte[] range(byte end) {
        return range((byte) 0, end);
    }
    public static byte[] range(byte start, byte end) {
        return range(start, end, (byte) 1);
    }
    public static byte[] range(byte start, byte end, byte step) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (start == end) return new byte[0];
        int size = (end - start) / step;
        if ((end - start) % step != 0) size++;
        byte[] range = new byte[size];
        for (byte i = 0, value = start; i < size; i++, value += step) range[i] = value;
        return range;
    }
    public static byte[] rangeLength(byte start, byte length) {
        return rangeLength(start, (byte) 1, length);
    }
    public static byte[] rangeLength(byte start, byte step, byte length) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (length == 0) return new byte[0];
        byte[] range = new byte[length];
        for (byte i = 0, value = start; i < length; i++, value += step) range[i] = value;
        return range;
    }
    
    public static short[] range(short end) {
        return range((short) 0, end);
    }
    public static short[] range(short start, short end) {
        return range(start, end, (short) 1);
    }
    public static short[] range(short start, short end, short step) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (start == end) return new short[0];
        int size = (end - start) / step;
        if ((end - start) % step != 0) size++;
        short[] range = new short[size];
        for (short i = 0, value = start; i < size; i++, value += step) range[i] = value;
        return range;
    }
    public static short[] rangeLength(short start, short length) {
        return rangeLength(start, (short) 1, length);
    }
    public static short[] rangeLength(short start, short step, short length) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (length == 0) return new short[0];
        short[] range = new short[length];
        for (short i = 0, value = start; i < length; i++, value += step) range[i] = value;
        return range;
    }
    
    public static int[] range(int end) {
        return range(0, end);
    }
    public static int[] range(int start, int end) {
        return range(start, end, 1);
    }
    public static int[] range(int start, int end, int step) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (start == end) return new int[0];
        int size = (end - start) / step;
        if ((end - start) % step != 0) size++;
        int[] range = new int[size];
        for (int i = 0, value = start; i < size; i++, value += step) range[i] = value;
        return range;
    }
    public static int[] rangeLength(int start, int length) {
        return rangeLength(start, 1, length);
    }
    public static int[] rangeLength(int start, int step, int length) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (length == 0) return new int[0];
        int[] range = new int[length];
        for (int i = 0, value = start; i < length; i++, value += step) range[i] = value;
        return range;
    }
    
    public static long[] range(long end) {
        return range(0, end);
    }
    public static long[] range(long start, long end) {
        return range(start, end, 1);
    }
    public static long[] range(long start, long end, long step) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (start == end) return new long[0];
        int size = (int) ((end - start) / step);
        if ((end - start) % step != 0) size++;
        long[] range = new long[size];
        for (int i = 0, value = (int) start; i < size; i++, value += step) range[i] = value;
        return range;
    }
    public static long[] rangeLength(long start, long length) {
        return rangeLength(start, 1, length);
    }
    public static long[] rangeLength(long start, long step, long length) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (length == 0) return new long[0];
        long[] range = new long[(int) length];
        for (int i = 0, value = (int) start; i < length; i++, value += step) range[i] = value;
        return range;
    }
    
    public static float[] range(float end) {
        return range(0, end);
    }
    public static float[] range(float start, float end) {
        return range(start, end, 1);
    }
    public static float[] range(float start, float end, float step) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (start == end) return new float[0];
        int size = (int) ((end - start) / step);
        if ((end - start) % step != 0) size++;
        float[] range = new float[size];
        for (int i = 0, value = (int) start; i < size; i++, value += step) range[i] = value;
        return range;
    }
    public static float[] rangeLength(float start, float length) {
        return rangeLength(start, 1, length);
    }
    public static float[] rangeLength(float start, float step, float length) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (length == 0) return new float[0];
        float[] range = new float[(int) length];
        for (int i = 0, value = (int) start; i < length; i++, value += step) range[i] = value;
        return range;
    }
    
    public static double[] range(double end) {
        return range(0, end);
    }
    public static double[] range(double start, double end) {
        return range(start, end, 1);
    }
    public static double[] range(double start, double end, double step) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (start == end) return new double[0];
        int size = (int) ((end - start) / step);
        if ((end - start) % step != 0) size++;
        double[] range = new double[size];
        for (int i = 0, value = (int) start; i < size; i++, value += step) range[i] = value;
        return range;
    }
    public static double[] rangeLength(double start, double length) {
        return rangeLength(start, 1, length);
    }
    public static double[] rangeLength(double start, double step, double length) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (length == 0) return new double[0];
        double[] range = new double[(int) length];
        for (int i = 0, value = (int) start; i < length; i++, value += step) range[i] = value;
        return range;
    }
    
    public static char[] range(char end) {
        return range((char) 0, end);
    }
    public static char[] range(char start, char end) {
        return range(start, end, (char) 1);
    }
    public static char[] range(char start, char end, char step) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (start == end) return new char[0];
        int size = (end - start) / step;
        if ((end - start) % step != 0) size++;
        char[] range = new char[size];
        for (char i = 0, value = start; i < size; i++, value += step) range[i] = value;
        return range;
    }
    public static char[] rangeLength(char start, char length) {
        return rangeLength(start, (char) 1, length);
    }
    public static char[] rangeLength(char start, char step, char length) {
        if (step == 0) throw new IllegalArgumentException("step cannot be zero");
        if (length == 0) return new char[0];
        char[] range = new char[length];
        for (char i = 0, value = start; i < length; i++, value += step) range[i] = value;
        return range;
    }
    
    public static byte[] repeat(byte value, int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        byte[] array = new byte[count];
        for (int i = 0; i < count; i++) array[i] = value;
        return array;
    }
    public static short[] repeat(short value, int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        short[] array = new short[count];
        for (int i = 0; i < count; i++) array[i] = value;
        return array;
    }
    public static int[] repeat(int value, int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        int[] array = new int[count];
        for (int i = 0; i < count; i++) array[i] = value;
        return array;
    }
    public static long[] repeat(long value, int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        long[] array = new long[count];
        for (int i = 0; i < count; i++) array[i] = value;
        return array;
    }
    public static float[] repeat(float value, int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        float[] array = new float[count];
        for (int i = 0; i < count; i++) array[i] = value;
        return array;
    }
    public static double[] repeat(double value, int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        double[] array = new double[count];
        for (int i = 0; i < count; i++) array[i] = value;
        return array;
    }
    public static char[] repeat(char value, int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        char[] array = new char[count];
        for (int i = 0; i < count; i++) array[i] = value;
        return array;
    }
    public static boolean[] repeat(boolean value, int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        boolean[] array = new boolean[count];
        for (int i = 0; i < count; i++) array[i] = value;
        return array;
    }
    public static <T> T[] repeat(T value, int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[count];
        for (int i = 0; i < count; i++) array[i] = value;
        return array;
    }
    public static <T> T[] repeat(T value, IntFunction<T[]> constructor, int count) {
        if (count < 0) throw new IllegalArgumentException("count cannot be negative");
        T[] array = constructor.apply(count);
        for (int i = 0; i < count; i++) array[i] = value;
        return array;
    }
    
    //TODO flat, map, filter, reduce, etc.
    public static byte[] flat(byte[]... arrays) {
        int size = 0;
        for (byte[] array : arrays) size += array.length;
        byte[] flat = new byte[size];
        for (int i = 0, j = 0; i < arrays.length; i++) {
            byte[] array = arrays[i];
            System.arraycopy(array, 0, flat, j, array.length);
            j += array.length;
        }
        return flat;
    }
    public static short[] flat(short[]... arrays) {
        int size = 0;
        for (short[] array : arrays) size += array.length;
        short[] flat = new short[size];
        for (int i = 0, j = 0; i < arrays.length; i++) {
            short[] array = arrays[i];
            System.arraycopy(array, 0, flat, j, array.length);
            j += array.length;
        }
        return flat;
    }
    public static int[] flat(int[]... arrays) {
        int size = 0;
        for (int[] array : arrays) size += array.length;
        int[] flat = new int[size];
        for (int i = 0, j = 0; i < arrays.length; i++) {
            int[] array = arrays[i];
            System.arraycopy(array, 0, flat, j, array.length);
            j += array.length;
        }
        return flat;
    }
    public static long[] flat(long[]... arrays) {
        int size = 0;
        for (long[] array : arrays) size += array.length;
        long[] flat = new long[size];
        for (int i = 0, j = 0; i < arrays.length; i++) {
            long[] array = arrays[i];
            System.arraycopy(array, 0, flat, j, array.length);
            j += array.length;
        }
        return flat;
    }
    public static float[] flat(float[]... arrays) {
        int size = 0;
        for (float[] array : arrays) size += array.length;
        float[] flat = new float[size];
        for (int i = 0, j = 0; i < arrays.length; i++) {
            float[] array = arrays[i];
            System.arraycopy(array, 0, flat, j, array.length);
            j += array.length;
        }
        return flat;
    }
    public static double[] flat(double[]... arrays) {
        int size = 0;
        for (double[] array : arrays) size += array.length;
        double[] flat = new double[size];
        for (int i = 0, j = 0; i < arrays.length; i++) {
            double[] array = arrays[i];
            System.arraycopy(array, 0, flat, j, array.length);
            j += array.length;
        }
        return flat;
    }
    public static char[] flat(char[]... arrays) {
        int size = 0;
        for (char[] array : arrays) size += array.length;
        char[] flat = new char[size];
        for (int i = 0, j = 0; i < arrays.length; i++) {
            char[] array = arrays[i];
            System.arraycopy(array, 0, flat, j, array.length);
            j += array.length;
        }
        return flat;
    }
    public static boolean[] flat(boolean[]... arrays) {
        int size = 0;
        for (boolean[] array : arrays) size += array.length;
        boolean[] flat = new boolean[size];
        for (int i = 0, j = 0; i < arrays.length; i++) {
            boolean[] array = arrays[i];
            System.arraycopy(array, 0, flat, j, array.length);
            j += array.length;
        }
        return flat;
    }
    @SafeVarargs
    public static <T> T[] flat(T[]... arrays) {
        int size = 0;
        for (T[] array : arrays) size += array.length;
        @SuppressWarnings("unchecked")
        T[] flat = (T[]) new Object[size];
        for (int i = 0, j = 0; i < arrays.length; i++) {
            T[] array = arrays[i];
            System.arraycopy(array, 0, flat, j, array.length);
            j += array.length;
        }
        return flat;
    }
    @SafeVarargs
    public static <T> T[] flat(IntFunction<T[]> constructor, T[]... arrays) {
        int size = 0;
        for (T[] array : arrays) size += array.length;
        T[] flat = constructor.apply(size);
        for (int i = 0, j = 0; i < arrays.length; i++) {
            T[] array = arrays[i];
            System.arraycopy(array, 0, flat, j, array.length);
            j += array.length;
        }
        return flat;
    }
    
    public static byte[] map(byte[] array, Int2ByteFunction mapper) {
        byte[] mapped = new byte[array.length];
        for (int i = 0; i < array.length; i++) mapped[i] = mapper.apply(i);
        return mapped;
    }
    public static short[] map(short[] array, Int2ShortFunction mapper) {
        short[] mapped = new short[array.length];
        for (int i = 0; i < array.length; i++) mapped[i] = mapper.apply(i);
        return mapped;
    }
    public static int[] map(int[] array, IntUnaryOperator mapper) {
        int[] mapped = new int[array.length];
        for (int i = 0; i < array.length; i++) mapped[i] = mapper.applyAsInt(i);
        return mapped;
    }
    public static long[] map(long[] array, Int2LongFunction mapper) {
        long[] mapped = new long[array.length];
        for (int i = 0; i < array.length; i++) mapped[i] = mapper.apply(i);
        return mapped;
    }
    public static float[] map(float[] array, Int2FloatFunction mapper) {
        float[] mapped = new float[array.length];
        for (int i = 0; i < array.length; i++) mapped[i] = mapper.apply(i);
        return mapped;
    }
    public static double[] map(double[] array, Int2DoubleFunction mapper) {
        double[] mapped = new double[array.length];
        for (int i = 0; i < array.length; i++) mapped[i] = mapper.apply(i);
        return mapped;
    }
    public static char[] map(char[] array, Int2CharFunction mapper) {
        char[] mapped = new char[array.length];
        for (int i = 0; i < array.length; i++) mapped[i] = mapper.apply(i);
        return mapped;
    }
    public static boolean[] map(boolean[] array, Int2BooleanFunction mapper) {
        boolean[] mapped = new boolean[array.length];
        for (int i = 0; i < array.length; i++) mapped[i] = mapper.apply(i);
        return mapped;
    }
    public static <T> T[] map(T[] array, IntFunction<T> mapper) {
        @SuppressWarnings("unchecked")
        T[] mapped = (T[]) new Object[array.length];
        for (int i = 0; i < array.length; i++) mapped[i] = mapper.apply(i);
        return mapped;
    }
    public static <T> T[] map(IntFunction<T[]> constructor, T[] array, IntFunction<T> mapper) {
        T[] mapped = constructor.apply(array.length);
        for (int i = 0; i < array.length; i++) mapped[i] = mapper.apply(i);
        return mapped;
    }
    
    public static byte[] filter(byte[] array, BytePredicate filter) {
        int size = 0;
        for (byte value : array) if (filter.test(value)) size++;
        byte[] filtered = new byte[size];
        for (int i = 0, j = 0; i < array.length; i++) {
            byte value = array[i];
            if (filter.test(value)) filtered[j++] = value;
        }
        return filtered;
    }
    public static short[] filter(short[] array, ShortPredicate filter) {
        int size = 0;
        for (short value : array) if (filter.test(value)) size++;
        short[] filtered = new short[size];
        for (int i = 0, j = 0; i < array.length; i++) {
            short value = array[i];
            if (filter.test(value)) filtered[j++] = value;
        }
        return filtered;
    }
    public static int[] filter(int[] array, IntPredicate filter) {
        int size = 0;
        for (int value : array) if (filter.test(value)) size++;
        int[] filtered = new int[size];
        for (int i = 0, j = 0; i < array.length; i++) {
            int value = array[i];
            if (filter.test(value)) filtered[j++] = value;
        }
        return filtered;
    }
    public static long[] filter(long[] array, LongPredicate filter) {
        int size = 0;
        for (long value : array) if (filter.test(value)) size++;
        long[] filtered = new long[size];
        for (int i = 0, j = 0; i < array.length; i++) {
            long value = array[i];
            if (filter.test(value)) filtered[j++] = value;
        }
        return filtered;
    }
    public static float[] filter(float[] array, FloatPredicate filter) {
        int size = 0;
        for (float value : array) if (filter.test(value)) size++;
        float[] filtered = new float[size];
        for (int i = 0, j = 0; i < array.length; i++) {
            float value = array[i];
            if (filter.test(value)) filtered[j++] = value;
        }
        return filtered;
    }
    public static double[] filter(double[] array, DoublePredicate filter) {
        int size = 0;
        for (double value : array) if (filter.test(value)) size++;
        double[] filtered = new double[size];
        for (int i = 0, j = 0; i < array.length; i++) {
            double value = array[i];
            if (filter.test(value)) filtered[j++] = value;
        }
        return filtered;
    }
    public static char[] filter(char[] array, CharPredicate filter) {
        int size = 0;
        for (char value : array) if (filter.test(value)) size++;
        char[] filtered = new char[size];
        for (int i = 0, j = 0; i < array.length; i++) {
            char value = array[i];
            if (filter.test(value)) filtered[j++] = value;
        }
        return filtered;
    }
    public static boolean[] filter(boolean[] array, BooleanPredicate filter) {
        int size = 0;
        for (boolean value : array) if (filter.test(value)) size++;
        boolean[] filtered = new boolean[size];
        for (int i = 0, j = 0; i < array.length; i++) {
            boolean value = array[i];
            if (filter.test(value)) filtered[j++] = value;
        }
        return filtered;
    }
    public static <T> T[] filter(T[] array, Predicate<T> filter) {
        int size = 0;
        for (T value : array) if (filter.test(value)) size++;
        @SuppressWarnings("unchecked")
        T[] filtered = (T[]) new Object[size];
        for (int i = 0, j = 0; i < array.length; i++) {
            T value = array[i];
            if (filter.test(value)) filtered[j++] = value;
        }
        return filtered;
    }
    public static <T> T[] filter(IntFunction<T[]> constructor, T[] array, Predicate<T> filter) {
        int size = 0;
        for (T value : array) if (filter.test(value)) size++;
        T[] filtered = constructor.apply(size);
        for (int i = 0, j = 0; i < array.length; i++) {
            T value = array[i];
            if (filter.test(value)) filtered[j++] = value;
        }
        return filtered;
    }
    
    public static byte reduce(byte[] array, byte identity, ByteBinaryOperator accumulator) {
        byte result = identity;
        for (byte value : array) result = accumulator.apply(result, value);
        return result;
    }
    public static short reduce(short[] array, short identity, ShortBinaryOperator accumulator) {
        short result = identity;
        for (short value : array) result = accumulator.apply(result, value);
        return result;
    }
    public static int reduce(int[] array, int identity, IntBinaryOperator accumulator) {
        int result = identity;
        for (int value : array) result = accumulator.applyAsInt(result, value);
        return result;
    }
    public static long reduce(long[] array, long identity, LongBinaryOperator accumulator) {
        long result = identity;
        for (long value : array) result = accumulator.applyAsLong(result, value);
        return result;
    }
    public static float reduce(float[] array, float identity, FloatBinaryOperator accumulator) {
        float result = identity;
        for (float value : array) result = accumulator.apply(result, value);
        return result;
    }
    public static double reduce(double[] array, double identity, DoubleBinaryOperator accumulator) {
        double result = identity;
        for (double value : array) result = accumulator.applyAsDouble(result, value);
        return result;
    }
    public static char reduce(char[] array, char identity, CharBinaryOperator accumulator) {
        char result = identity;
        for (char value : array) result = accumulator.apply(result, value);
        return result;
    }
    public static boolean reduce(boolean[] array, boolean identity, BooleanBinaryOperator accumulator) {
        boolean result = identity;
        for (boolean value : array) result = accumulator.apply(result, value);
        return result;
    }
    public static <T> T reduce(T[] array, T identity, BinaryOperator<T> accumulator) {
        T result = identity;
        for (T value : array) result = accumulator.apply(result, value);
        return result;
    }
    public static <T, U> U reduce(T[] array, U identity, BiFunction<U, T, U> accumulator) {
        U result = identity;
        for (T value : array) result = accumulator.apply(result, value);
        return result;
    }
    
    public static byte reduce(byte[] array, ByteBinaryOperator accumulator) {
        byte result = array[0];
        for (int i = 1; i < array.length; i++) result = accumulator.apply(result, array[i]);
        return result;
    }
    public static short reduce(short[] array, ShortBinaryOperator accumulator) {
        short result = array[0];
        for (int i = 1; i < array.length; i++) result = accumulator.apply(result, array[i]);
        return result;
    }
    public static int reduce(int[] array, IntBinaryOperator accumulator) {
        int result = array[0];
        for (int i = 1; i < array.length; i++) result = accumulator.applyAsInt(result, array[i]);
        return result;
    }
    public static long reduce(long[] array, LongBinaryOperator accumulator) {
        long result = array[0];
        for (int i = 1; i < array.length; i++) result = accumulator.applyAsLong(result, array[i]);
        return result;
    }
    public static float reduce(float[] array, FloatBinaryOperator accumulator) {
        float result = array[0];
        for (int i = 1; i < array.length; i++) result = accumulator.apply(result, array[i]);
        return result;
    }
    public static double reduce(double[] array, DoubleBinaryOperator accumulator) {
        double result = array[0];
        for (int i = 1; i < array.length; i++) result = accumulator.applyAsDouble(result, array[i]);
        return result;
    }
    public static char reduce(char[] array, CharBinaryOperator accumulator) {
        char result = array[0];
        for (int i = 1; i < array.length; i++) result = accumulator.apply(result, array[i]);
        return result;
    }
    public static boolean reduce(boolean[] array, BooleanBinaryOperator accumulator) {
        boolean result = array[0];
        for (int i = 1; i < array.length; i++) result = accumulator.apply(result, array[i]);
        return result;
    }
    public static <T> T reduce(T[] array, BinaryOperator<T> accumulator) {
        T result = array[0];
        for (int i = 1; i < array.length; i++) result = accumulator.apply(result, array[i]);
        return result;
    }
    
    public static byte[] generateByte(int size, Int2ByteFunction mapper) {
        byte[] array = new byte[size];
        for (int i = 0; i < size; i++) array[i] = mapper.apply(i);
        return array;
    }
    public static short[] generateShort(int size, Int2ShortFunction mapper) {
        short[] array = new short[size];
        for (int i = 0; i < size; i++) array[i] = mapper.apply(i);
        return array;
    }
    public static int[] generate(int size, IntUnaryOperator mapper) {
        return map(range(size), mapper);
    }
    public static long[] generate(int size, Int2LongFunction mapper) {
        return map(range((long)size), mapper);
    }
    public static float[] generate(int size, Int2FloatFunction mapper) {
        float[] array = new float[size];
        for (int i = 0; i < size; i++) array[i] = mapper.apply(i);
        return array;
    }
    public static double[] generate(int size, Int2DoubleFunction mapper) {
        return map(range((double)size), mapper);
    }
    public static char[] generate(int size, Int2CharFunction mapper) {
        char[] array = new char[size];
        for (int i = 0; i < size; i++) array[i] = mapper.apply(i);
        return array;
    }
    public static boolean[] generate(int size, Int2BooleanFunction mapper) {
        boolean[] array = new boolean[size];
        for (int i = 0; i < size; i++) array[i] = mapper.apply(i);
        return array;
    }
    public static <T> T[] generate(int size, IntFunction<T> mapper) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[size];
        for (int i = 0; i < size; i++) array[i] = mapper.apply(i);
        return array;
    }
    public static <T> T[] generate(IntFunction<T[]> constructor, int size, IntFunction<T> mapper) {
        T[] array = constructor.apply(size);
        for (int i = 0; i < size; i++) array[i] = mapper.apply(i);
        return array;
    }
    
    public static short[] charToShort(char[] array) {
        short[] converted = new short[array.length];
        for (int i = 0; i < array.length; i++) converted[i] = (short) array[i];
        return converted;
    }
    public static char[] shortToChar(short[] array) {
        char[] converted = new char[array.length];
        for (int i = 0; i < array.length; i++) converted[i] = (char) array[i];
        return converted;
    }
    public static int[] floatToInt(float[] array) {
        int[] converted = new int[array.length];
        for (int i = 0; i < array.length; i++) converted[i] = Float.floatToRawIntBits(array[i]);
        return converted;
    }
    public static float[] intToFloat(int[] array) {
        float[] converted = new float[array.length];
        for (int i = 0; i < array.length; i++) converted[i] = Float.intBitsToFloat(array[i]);
        return converted;
    }
    public static long[] doubleToLong(double[] array) {
        long[] converted = new long[array.length];
        for (int i = 0; i < array.length; i++) converted[i] = Double.doubleToRawLongBits(array[i]);
        return converted;
    }
    public static double[] longToDouble(long[] array) {
        double[] converted = new double[array.length];
        for (int i = 0; i < array.length; i++) converted[i] = Double.longBitsToDouble(array[i]);
        return converted;
    }
}
