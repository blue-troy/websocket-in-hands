package club.bluetroy;

import java.io.OutputStream;

/**
 * @author heyixin 2019-05-16 10:35.
 */
public interface OutputHandler {
    void handle(Object input, OutputStream outputStream);
}
