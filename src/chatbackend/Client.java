package chatbackend;
import java.io.IOException;
import java.net.Socket;

public class Client
{
    static Socket socket;
    public static void main(String[] args) throws IOException {
        socket = new Socket("localhost",5000);
        new ClientSends(socket).start();
        new ClientReceives(socket).start();
    }
}