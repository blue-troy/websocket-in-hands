package club.bluetroy.util;

import club.bluetroy.http.HttpRequest;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author heyixin 2019-05-16 14:25.
 */
@UtilityClass
public class HttpRequestParser {
    public HttpRequest parseHttpRequest(String httpString) {
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
        Map<String, String> head = new HashMap<>(5);
        for (String s : headArray) {
            String[] headKeyValue = s.split(":");
            String key = headKeyValue[0].strip();
            String value = (headKeyValue.length > 1) ? headKeyValue[1].strip() : "";
            head.put(key, value);
        }
        return head;
    }

    private String getRequestLine(String httpString) {
        return httpString.substring(0, httpString.indexOf("\r\n"));
    }
}
