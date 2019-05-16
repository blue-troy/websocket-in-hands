package club.bluetroy;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author heyixin 2019-05-16 11:14.
 */
@Slf4j
public class IOHandler {
    private InputHandler inputHandler;
    private OutputHandler outputHandler;
    private MessageResolver messageResolver;

    public void handleIO(InputStream inputStream, OutputStream outputStream) {
        //todo 暂时采用捕获异常并打印的方式，应用中应当捕获异常并处理
        try {
            Object input = inputHandler.handle(inputStream);
            Object output = messageResolver.resolve(input, this);
            outputHandler.handle(output, outputStream);
        } catch (Exception e) {
            log.warn("IOHandler exception",e);
        }

    }
}
