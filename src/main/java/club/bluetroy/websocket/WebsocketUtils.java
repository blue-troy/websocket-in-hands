package club.bluetroy.websocket;

import club.bluetroy.http.HttpRequest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

/**
 * @author heyixin 2019-05-16 13:08.
 * /*GET /chat HTTP/1.1
 * Host: example.com:8000
 * Upgrade: websocket
 * Connection: Upgrade
 * Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ==
 * Sec-WebSocket-Version: 13
 * <p>
 * HTTP/1.1 101 Switching Protocols
 * Upgrade: websocket
 * Connection: Upgrade
 * Sec-WebSocket-Accept: s3pPLMBiTxaQ9kYGzzhZRbK+xOo=
 */
public class WebsocketUtils {
    private static void handle(HttpRequest httpRequest, OutputStream out) throws IOException, NoSuchAlgorithmException {
        if (isWebsocketUpgrade(httpRequest)) {
            String response = "HTTP/1.1 101 Switching Protocols\r\n" +
                    "    Upgrade: websocket\r\n" +
                    "    Connection: Upgrade\r\n" +
                    "    Sec-WebSocket-Accept: " + getSec(httpRequest) +
                    "\r\n\r\n";
            PrintWriter outWriter = new PrintWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8), true);
            outWriter.println(response);
        }
    }

    private static boolean isWebsocketUpgrade(HttpRequest httpRequest) {
        Map<String, String> head = httpRequest.getHead();
        return "websocket".equals(head.get("Upgrade")) && "Upgrade".equals(head.get("Connection"));
    }

    private static String getSec(HttpRequest httpRequest) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Map<String, String> head = httpRequest.getHead();
        return Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-1").digest((head.get("Sec-WebSocket-Key") + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes(StandardCharsets.UTF_8)));
    }
}
