package chatbackend;
import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain
{
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4000);
        RequestAcceptor ra = new RequestAcceptor(serverSocket);
        ra.start();
    }
}










