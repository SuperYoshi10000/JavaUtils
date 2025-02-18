package local.ytk.util.reflect;

import local.ytk.util.function.FunctionUtils;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class AccessorsTest {
    
    @Test
    void get() {
    }
    
    @Test
    void set() {
    }
    
    @Test
    void constructor() {
    }
    
    @Test
    void newInstance() {
    
    }
    
    @Test
    void aaa1() {
        Class<?> cls1;
        Class<?> aClass = (new AbstractSet<>() {
            @Override
            public Iterator<Object> iterator() {
                return new Iterator<>() {
                    @Override
                    public boolean hasNext() {
                        return false;
                    }
                    
                    @Override
                    public Object next() {
                        return null;
                    }
                };
            }
            
            @Override
            public int size() {
                return 0;
            }
        }).iterator().getClass();
        System.out.println(Accessors.betterClassName(aClass));
        System.out.println(Accessors.betterClassName(new Object() {}));
        System.out.println(Accessors.betterClassName(new Serializable() {}));
        class Test1 {}
        System.out.println(Accessors.betterClassName(Test1.class));
        System.out.println(Accessors.betterClassName(FunctionUtils.function(x -> 0)));
        System.out.println(Accessors.betterClassName(FunctionUtils.function(new ArrayList<>()::add)));
        System.out.println(Accessors.betterClassName(FunctionUtils.supplier(ArrayList::new)));
        
        
    }
}