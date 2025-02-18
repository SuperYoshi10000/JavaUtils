package local.ytk.util.reflect;

public enum Visibility {
    PUBLIC("public"),
    PROTECTED("protected"),
    PACKAGE_PRIVATE(""),
    PRIVATE("private");
    
    public final String name;
    
    Visibility(String s) {
        name = s;
    }
    
    @Override
    public String toString() {
        return name.isEmpty() ? "" : " " + name;
    }
}
