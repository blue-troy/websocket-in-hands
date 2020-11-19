package club.bluetroy.http;

import club.bluetroy.IOHandler;
import club.bluetroy.InputHandler;
import club.bluetroy.MessageResolver;
import club.bluetroy.OutputHandler;
import club.bluetroy.websocket.WebsocketIOHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author heyixin 2019-05-16 15:41.
 */
@Slf4j
public class HttpIOHandler implements IOHandler {
    private InputHandler inputHandler = new HttpInputHandler();
    private OutputHandler outputHandler = new HttpOutputHandler();
    private MessageResolver messageResolver = new HandShaker();

    @Override
    public void handleIO(InputStream inputStream, OutputStream outputStream) {
        //todo 暂时采用捕获异常并打印的方式，应用中应当捕获异常并处理
        try {
            Object input = inputHandler.handle(inputStream);
            Object output = messageResolver.resolve(input, this);
            outputHandler.handle(output, outputStream);
            //websocket握手后http阶段任务完成,转换成websocket协议
            new WebsocketIOHandler().handleIO(inputStream, outputStream);
        } catch (Exception e) {
            log.warn("IOHandler exception", e);
        }

    }
}
