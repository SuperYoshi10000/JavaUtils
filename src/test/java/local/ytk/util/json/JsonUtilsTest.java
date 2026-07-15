package local.ytk.util.json;

import org.junit.jupiter.api.Test;

import static local.ytk.util.json.JsonUtils.*;
import static local.ytk.util.function.ThrowingFunctions.*;

class JsonUtilsTest {
    @Test
    void test() {
        System.out.println(nonThrowing(()->parse("")).get());
        System.out.println(nonThrowing(()->parse("a")).get());
        System.out.println(parse("\"aaa\""));
        System.out.println(parse("1234"));
        System.out.println(parse("false"));
        System.out.println(parse("[]"));
        System.out.println(parse("{}"));
        
    }
}