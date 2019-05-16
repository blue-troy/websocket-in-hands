package club.bluetroy.http;

import club.bluetroy.IOHandler;
import club.bluetroy.MessageResolver;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author heyixin 2019-05-16 13:37.
 */
public class HandShaker implements MessageResolver {
    @Override
    public Object resolve(Object input, IOHandler ioHandler) {
        HttpRequest request = (HttpRequest) input;
        return getHandShakeResponse(request);
    }

    /**
     * get http response like:
     * HTTP/1.1 101 Switching Protocols
     * Upgrade: websocket
     * Connection: Upgrade
     * Sec-WebSocket-Accept: s3pPLMBiTxaQ9kYGzzhZRbK+xOo=
     *
     * @param request httpRequest
     * @return httpResponse
     */
    private HttpResponse getHandShakeResponse(HttpRequest request) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatusLine("HTTP/1.1 101 Switching Protocols");
        httpResponse.setHead(getResponseHead(request));
        return httpResponse;
    }

    private Map<String, String> getResponseHead(HttpRequest request) {
        Map<String, String> responseHead = new HashMap<>(3);
        responseHead.put("Upgrade", "websocket");
        responseHead.put("Connection", "Upgrade");
        responseHead.put("Sec-WebSocket-Accept", getSec(request));
        return responseHead;
    }

    private String getSec(HttpRequest httpRequest) {
        try {
            Map<String, String> head = httpRequest.getHead();
            return Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-1").digest((head.get("Sec-WebSocket-Key") + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
