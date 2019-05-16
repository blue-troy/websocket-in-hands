package club.bluetroy.http;

import lombok.Data;

import java.util.Map;

/**
 * @author heyixin 2019-05-16 13:05.
 */
@Data
public class HttpRequest {
    private String requestLine;
    private Map<String, String> head;
    private String content;
}
