package club.bluetroy.http;

import club.bluetroy.OutputHandler;
import club.bluetroy.util.HttpResponseParser;
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
            HttpResponse httpResponse = (HttpResponse) input;
            String responseString = HttpResponseParser.parseHttpResponse(httpResponse);
            outputStream.write(responseString.getBytes(StandardCharsets.UTF_8));
            log.info("http response:\r\n{}", responseString);
            outputStream.flush();
        } catch (IOException e) {
            log.warn("http out put handler exception", e);
        }
    }
}
