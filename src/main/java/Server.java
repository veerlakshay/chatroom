import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{

    private ArrayList<ConnectionHandler> connections;


    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(9999);
            Socket client = server.accept();
            ConnectionHandler handler = new ConnectionHandler(client);
            connections.add(handler);

        } catch (IOException e) {
            // TODO : handle
        }
    }

    public void broadcast(String message) {
        for(ConnectionHandler ch : connections){
            if(ch != null) {
                ch.sendMessage(message);
            }
        }
    }

    class ConnectionHandler implements  Runnable {

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String nickname;


        public ConnectionHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(client.getOutputStream() , true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.println("Please enter your nickname : ");
                nickname = in.readLine();
                System.out.println(nickname + "connected");
                broadcast(nickname + "joined the chat!!!!");
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.startsWith("/nick ")){
                        //TODO : handle nickname
                    }else if(message.startsWith("/quit")){
                        //TODO : quit
                    } else {
                        broadcast(nickname + ": " + message);
                    }
                }
            } catch (IOException e) {
                //TODO : handle
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }
    }

}