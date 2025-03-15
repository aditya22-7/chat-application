package chatbackend;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestAcceptor extends Thread
{
    ServerSocket serverSocket;
    BufferedReader input;
    public RequestAcceptor(ServerSocket ss)
    {
        this.serverSocket=ss;
    }
    public void run()
    {
        while(true)
        {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected");
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String username = input.readLine();
            String selectedUser = input.readLine();
            SocketStore ss = new SocketStore(username,selectedUser,socket);
            ss.storeInMap();
            ss.initiateThread();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}