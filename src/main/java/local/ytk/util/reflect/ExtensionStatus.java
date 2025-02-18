package local.ytk.util.reflect;

public enum ExtensionStatus {
    DEFAULT(""),
    SEALED("sealed"),
    NON_SEALED("non-sealed"),
    FINAL("final");
    
    public final String name;
    
    ExtensionStatus(String s) {
        name = s;
    }
    
    @Override
    public String toString() {
        return name.isEmpty() ? "" : " " + name;
    }
    
    public boolean canExtend() {
        return this == DEFAULT || this == NON_SEALED;
    }
}
