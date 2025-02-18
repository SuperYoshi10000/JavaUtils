package local.ytk.util.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static local.ytk.util.json.JsonUtils.*;
import static local.ytk.util.function.ThrowableFunctions.*;

class JsonUtilsTest {
    @Test
    void test() {
        System.out.println(nonThrowable(()->parse("")).get());
        System.out.println(nonThrowable(()->parse("a")).get());
        System.out.println(parse("\"aaa\""));
        System.out.println(parse("1234"));
        System.out.println(parse("false"));
        System.out.println(parse("[]"));
        System.out.println(parse("{}"));
        
    }
}