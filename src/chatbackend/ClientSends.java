package chatbackend;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSends extends Thread
{
    String username,selectedUser;
    PrintWriter output;
    Socket socket;
    public ClientSends(Socket socket){
        this.socket=socket;
    }
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        try {
            output = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Enter username");
        username= sc.nextLine();
        output.println(username);
        System.out.println("Enter whom you want to send message");
        selectedUser=sc.nextLine();
        output.println(selectedUser);
        while(true)
        {
            System.out.println("Enter Message");
            String str = sc.nextLine();
            if(str.equals("reset")){
                output.println(str);
                System.out.println("Enter whom you want to send message");
                selectedUser = sc.nextLine();
                output.println(selectedUser);
            }
            else
            output.println(username+": "+str);
        }
    }
}