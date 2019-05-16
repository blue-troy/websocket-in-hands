package club.bluetroy.http;

import lombok.Data;

import java.util.Map;

/**
 * @author heyixin 2019-05-16 13:41.
 */
@Data
public class HttpResponse {
    private String statusLine;
    private Map<String, String> head;
    private String content;
}
