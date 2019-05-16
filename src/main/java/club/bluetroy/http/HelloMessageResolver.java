package club.bluetroy.http;

import club.bluetroy.IOHandler;
import club.bluetroy.MessageResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author heyixin 2019-05-16 14:30.
 */
public class HelloMessageResolver implements MessageResolver {
    @Override
    public Object resolve(Object input, IOHandler ioHandler) {
        HttpResponse httpResponse = new HttpResponse();

        httpResponse.setStatusLine("HTTP/1.1 200 OK");

        String content = "hello world";
        httpResponse.setContent(content);

        Map<String, String> head = new HashMap<>(1);
        head.put("Connection", "Close");
        head.put("Content-Length", String.valueOf(content.getBytes().length));
        httpResponse.setHead(head);

        return httpResponse;
    }
}
