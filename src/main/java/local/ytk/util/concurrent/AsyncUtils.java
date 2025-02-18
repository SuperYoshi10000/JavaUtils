package local.ytk.util.concurrent;

import local.ytk.util.annotation.Static;

import java.util.Optional;
import java.util.concurrent.Future;

@Static
public class AsyncUtils {
    private AsyncUtils() {}
    
    public static <V> Optional<V> getIfDone(Future<V> future) {
        return future.isDone() ? Optional.of(future.resultNow()) : Optional.empty();
    }
}
