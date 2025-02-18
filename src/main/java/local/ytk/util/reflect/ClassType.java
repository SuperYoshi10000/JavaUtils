package local.ytk.util.reflect;

public enum ClassType {
    NON_CLASS(""),
    CLASS("class"),
    ABSTRACT_CLASS("abstract class"),
    INTERFACE("interface"),
    RECORD("record"),
    ENUM("enum"),
    ANNOTATION("@interface");
    
    public final String name;
    
    ClassType(String s) {
        name = s;
    }
    
    @Override
    public String toString() {
        return name.isEmpty() ? "" : " " + name;
    }
    
    public boolean canBeFinal() {
        return this == CLASS || this == RECORD || this == ENUM;
    }
    public boolean canExtend() {
        return this == INTERFACE || this == ABSTRACT_CLASS || this == CLASS || this == ANNOTATION;
    }
    public boolean isClass() {
        return this == CLASS || this == ABSTRACT_CLASS;
    }
    public boolean isInterface() {
        return this == INTERFACE || this == ANNOTATION;
    }
}
