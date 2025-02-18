package local.ytk.util.ref;

public interface Reference<T> {
    void set(T value);
    T get();
}
