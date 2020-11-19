package club.bluetroy;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author heyixin 2019-05-16 11:14.
 */
public interface IOHandler {
    void handleIO(InputStream inputStream, OutputStream outputStream);
}
