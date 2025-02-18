package local.ytk.util.function;

import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.System.*;

import static local.ytk.util.function.FunctionUtils.*;

class FunctionUtilsTest {
    public final Consumer<Dummy> dummyConsumer = a -> outputString = a.toString();
    public final BiConsumer<Dummy1, Dummy2> dummyBiConsumer = (a, b) -> outputString = a + ", " + b;
    public static final Function<Dummy, String> dummyFunction = Object::toString;
    public static final BiFunction<Dummy1, Dummy2, String> dummyBiFunction = (a, b) -> a + ", " + b;
    public static final Dummy1 DUMMY_1 = new Dummy1();
    public static final Dummy2 DUMMY_2 = new Dummy2();
    public static final String DUMMY_1_STR = "DUMMY_1";
    public static final String DUMMY_2_STR = "DUMMY_2";
    public static final String DUMMY_12_STR = "DUMMY_1, DUMMY_2";
    String outputString;
    
    void init() {
//        setOut(OUTPUT_STREAM);
    }
    
    @Test
    void testBind() {
        init();
        
        bind(dummyConsumer, DUMMY_1).run();
        assertEquals(DUMMY_1_STR, outputString);
        assertEquals(DUMMY_1_STR, bind(dummyFunction, DUMMY_1).get());
        
        bind(dummyBiConsumer, DUMMY_1, DUMMY_2).run();
        assertEquals(DUMMY_12_STR, outputString);
        assertEquals(DUMMY_12_STR, bind(dummyBiFunction, DUMMY_1, DUMMY_2).get());
        
        bind1(dummyBiConsumer, DUMMY_1).accept(DUMMY_2);
        assertEquals(DUMMY_12_STR, outputString);
        bind2(dummyBiConsumer, DUMMY_2).accept(DUMMY_1);
        assertEquals(DUMMY_12_STR, outputString);
        assertEquals(DUMMY_12_STR, bind1(dummyBiFunction, DUMMY_1).apply(DUMMY_2));
        assertEquals(DUMMY_12_STR, bind2(dummyBiFunction, DUMMY_2).apply(DUMMY_1));
    }
    
    @Test
    void testCurry() {
        init();
        curry1(dummyBiConsumer).apply(DUMMY_1).accept(DUMMY_2);
        assertEquals(DUMMY_12_STR, outputString);
        curry2(dummyBiConsumer).apply(DUMMY_2).accept(DUMMY_1);
        assertEquals(DUMMY_12_STR, outputString);
        assertEquals(DUMMY_12_STR, curry1(dummyBiFunction).apply(DUMMY_1).apply(DUMMY_2));
        assertEquals(DUMMY_12_STR, curry2(dummyBiFunction).apply(DUMMY_2).apply(DUMMY_1));
    }
    
    public interface Dummy {}
    public static class Dummy1 implements Dummy {
        @Override
        public String toString() {
            return DUMMY_1_STR;
        }
    }
    public static class Dummy2 implements Dummy {
        @Override
        public String toString() {
            return DUMMY_2_STR;
        }
    }
}