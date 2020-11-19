package club.bluetroy.websocket;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.junit.Assert.*;

/**
 * @author heyixin 2019-05-17 09:47.
 */
public class WebsocketOutputHandlerTest {

    @Test
    public void handle() {
        OutputStream outputStream = new ByteArrayOutputStream();
        WebsocketOutputHandler websocketOutputHandler = new WebsocketOutputHandler();
        websocketOutputHandler.handle(new Frame(), outputStream);
    }
}