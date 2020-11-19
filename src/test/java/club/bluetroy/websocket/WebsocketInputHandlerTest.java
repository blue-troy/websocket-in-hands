package club.bluetroy.websocket;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author heyixin 2019-05-16 17:21.
 */
public class WebsocketInputHandlerTest {

    @Test
    public void handle() throws Exception {
        //todo 手动测试改成自动
        InputStream inputStream = new ByteArrayInputStream(new byte[]{(byte) 129, (byte) 134, (byte) 167, (byte) 225, (byte) 225, (byte) 210, (byte) 198, (byte) 131, (byte) 130, (byte) 182, (byte) 194, (byte) 135});
        WebsocketInputHandler websocketInputHandler = new WebsocketInputHandler();
        Frame handle = websocketInputHandler.handle(inputStream);
        System.out.println(handle);
    }
}