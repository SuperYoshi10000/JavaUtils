package local.ytk.util.ref;

import java.nio.IntBuffer;

public class IntReference {
    protected final int[] intArray = new int[]{0};
    protected final IntBuffer intBuffer = IntBuffer.wrap(intArray);
    
    public IntReference() {}
    public IntReference(int value) {
        set(value);
    }
    
    public IntBuffer intBuffer() {
        return intBuffer;
    }
    
    public int[] intArray() {
        return intArray;
        
    }

    public int set(int value) {
        return intArray[0] = value;
    }
    public int get() {
        return intArray[0];
    }
}
