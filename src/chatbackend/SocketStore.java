package chatbackend;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketStore
{
    static HashMap<String,SocketInnerStore> map = new HashMap<>();
    Socket socket;
    String username;
    String selectedUser;

    public SocketStore(String username, String selectedUser, Socket socket){
        this.username=username;
        this.selectedUser=selectedUser;
        this.socket=socket; 
    }
    public void initiateThread() throws IOException {
        SocketInnerStore t = new SocketInnerStore();
        t.start();
    }

    public void storeInMap() throws IOException {
        map.put(username,new SocketInnerStore());
    }

    public class SocketInnerStore extends Thread
    {
        PrintWriter output;
        BufferedReader input;

        public SocketInnerStore() throws IOException {
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new PrintWriter(socket.getOutputStream(),true);
        }
        public void run(){
            currentThread().setName("sis thread");
            try {
                while (true) {
                    String str=input.readLine();
                    if(str.equals("reset")){
                        selectedUser = input.readLine();
                    }
                    else
                    sendMessage(str);
                }
            }catch(IOException e){
                System.out.println("Error in Server Receiver !!!");
            }
        }
        public void sendMessage(String str) throws IOException {
            for(Map.Entry<String,SocketInnerStore> e : map.entrySet()){
                if(e.getKey().equals(selectedUser)){
                    SocketInnerStore sis = e.getValue();
                    sis.output.println(str);
                }
            }
        }
    }
}