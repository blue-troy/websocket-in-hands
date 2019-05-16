package club.bluetroy.util;

import club.bluetroy.http.HttpResponse;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Optional;

/**
 * http response 解析器.
 *
 * @author heyixin 2019-05-16 14:22.
 */
@UtilityClass
public class HttpResponseParser {
    /**
     * 将httpResponse对象解析成字符串
     *
     * @param httpResponse httpResponse
     * @return response string
     */
    public String parseHttpResponse(HttpResponse httpResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        parseStatusLine(httpResponse, stringBuilder);
        parseHead(httpResponse, stringBuilder);
        parseContent(httpResponse, stringBuilder);
        return stringBuilder.toString();
    }

    private void parseStatusLine(HttpResponse httpResponse, StringBuilder stringBuilder) {
        stringBuilder.append(httpResponse.getStatusLine()).append("\r\n");
    }

    private void parseHead(HttpResponse httpResponse, StringBuilder stringBuilder) {
        Map<String, String> head = httpResponse.getHead();
        head.forEach((k, v) -> stringBuilder.append(k).append(":").append(v).append("\r\n"));
        stringBuilder.append("\r\n");
    }

    private void parseContent(HttpResponse httpResponse, StringBuilder stringBuilder) {
        Optional.ofNullable(httpResponse.getContent()).ifPresent(stringBuilder::append);
    }
}
