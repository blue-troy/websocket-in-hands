package club.bluetroy;

import club.bluetroy.http.HttpIOHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author heyixin 2019-05-16 10:37.
 */
@Slf4j
public class Server {
    private int port;
    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            this.port = port;
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        try {
            log.info("Server has started on 127.0.0.1:"+port+".\r\nWaiting for a connection...");
            while (true) {
                Socket client = serverSocket.accept();
                client.setKeepAlive(true);
                try (InputStream inputStream = client.getInputStream();
                     OutputStream outputStream = client.getOutputStream()) {
                    new HttpIOHandler().handleIO(inputStream, outputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
