package club.bluetroy.http;

import club.bluetroy.InputHandler;
import club.bluetroy.util.HttpRequestParser;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author heyixin 2019-05-16 11:27.
 */
@Slf4j
public class HttpInputHandler implements InputHandler {
    @Override
    public HttpRequest handle(InputStream inputStream) throws Exception {
        String requestString = getRequestString(inputStream);
        return HttpRequestParser.parseHttpRequest(requestString);
    }

    private String getRequestString(InputStream inputStream) throws IOException, InterruptedException {
        try (BufferedInputStream in = new BufferedInputStream(inputStream)) {
            //暂时使用这种等待的方式等待Input可读
            TimeUnit.SECONDS.sleep(1);
            String inputHttp = new String(in.readAllBytes(), Charset.forName("utf-8"));
            log.info("http input:{}", inputHttp);
            return inputHttp;
        }
    }
}
