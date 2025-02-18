package local.ytk.util;

import java.io.Closeable;

public interface FlaggedCloseable extends AutoCloseable {
    boolean isClosed();
}
