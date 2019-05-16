package club.bluetroy;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author heyixin 2019-05-16 10:34.
 */
public interface InputHandler {
    /**
     * handle input to string
     * @param inputStream inputStream
     * @return inputObject
     */
    Object handle(InputStream inputStream) throws Exception;
}
