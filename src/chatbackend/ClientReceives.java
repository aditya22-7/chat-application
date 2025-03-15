package chatbackend;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceives extends Thread
{
    BufferedReader input;
    Socket socket;
    public ClientReceives(Socket socket){
        this.socket=socket;
    }
    public void run()
    {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()) );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(true)
        {
            try {
                String str = input.readLine();
                System.out.println(str);
                if(str.equals("bye")){
                    socket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}