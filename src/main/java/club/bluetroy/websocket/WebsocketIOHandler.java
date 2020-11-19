package club.bluetroy.websocket;

import club.bluetroy.IOHandler;
import club.bluetroy.InputHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author heyixin 2019-05-16 15:43.
 */
@Slf4j
public class WebsocketIOHandler implements IOHandler {
    private InputHandler inputHandler = new WebsocketInputHandler();
    @Override
    public void handleIO(InputStream inputStream, OutputStream outputStream) {
        while (true) {
            try {
                waitUntilInputAvalible(inputStream);
                Frame handle = (Frame)inputHandler.handle(inputStream);
                System.out.println(handle.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void waitUntilInputAvalible(InputStream inputStream) {
        while (true) {
            try {
                if (inputStream.available() > 0) {
                    break;
                } else {
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
