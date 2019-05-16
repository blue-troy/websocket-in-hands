package club.bluetroy.http;

import club.bluetroy.InputHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author heyixin 2019-05-16 11:27.
 */
@Slf4j
public class HttpInputHandler implements InputHandler {
    @Override
    public HttpRequest handle(InputStream inputStream) throws Exception {
        String requestString = getRequestString(inputStream);
        return parseHttpRequest(requestString);
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


    private HttpRequest parseHttpRequest(String httpString) {
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setRequestLine(getRequestLine(httpString));
        httpRequest.setHead(getRequestHead(httpString));
        httpRequest.setContent(getHttpContent(httpString));
        return httpRequest;
    }

    private String getHttpContent(String httpString) {
        return httpString.substring(httpString.indexOf("\r\n\r\n"));
    }

    private Map<String, String> getRequestHead(String httpString) {
        String headString = httpString.substring(httpString.indexOf("\r\n"), httpString.indexOf("\r\n\r\n"));
        String[] headArray = headString.split("\r\n");
        Map<String,String> head = new HashMap<>(5);
        for (String s : headArray) {
            String[] headKeyValue = s.split(":");
            String key = headKeyValue[0].strip();
            String value = (headKeyValue.length > 1) ? headKeyValue[1].strip() : "";
            head.put(key, value);
        }
        return head;
    }

    private String getRequestLine(String httpString) {
        return httpString.substring(0,httpString.indexOf("\r\n"));
    }
}
