package club.bluetroy;

/**
 * @author heyixin 2019-05-16 14:37.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Server server = new Server(8080);
        server.start();
    }
}
