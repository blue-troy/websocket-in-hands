package club.bluetroy.http;

import club.bluetroy.OutputHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author heyixin 2019-05-16 13:03.
 */
@Slf4j
public class HttpOutputHandler implements OutputHandler {
    @Override
    public void handle(Object input, OutputStream outputStream) {
        try {
            String html = "<html>hello world</html>";
            int length = html.getBytes().length;
            String hello = "HTTP/1.1 200 OK\r\nContent-Type:text/html\r\nContent-Length:" + length + "\r\nConnection:Close" + "\r\n\r\n" + html;
            outputStream.write(hello.getBytes(StandardCharsets.UTF_8));
            log.info("http response:\r\n{}", hello);
            outputStream.flush();
        } catch (IOException e) {
            log.warn("http out put handler exception",e);
        }
    }
}
