package client;

import server.ServerChatInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

//connect the client to the server
public class ClientChatDriver {
    public static final String UNIQUE_BINDING_NAME = "server.chat_rmi";

    public static void main(String[] args) throws IOException, RemoteException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your name: ");
        String username = br.readLine().trim();

        try {
            final Registry registry = LocateRegistry.getRegistry(null, 12345);
            final ServerChatInterface server = (ServerChatInterface) registry.lookup(UNIQUE_BINDING_NAME);
            final ClientChatInterface client = new ClientChat(username, server);
            server.register(client);
            server.sendPrivateMessage("Server", client.getUsername(), "You can send private message, please enter the phrase 'private'");

            Scanner scanner = new Scanner(System.in);
            String message = "";

            while(true){
                message = scanner.nextLine().trim();

                //catch private message in console
                if (message.equalsIgnoreCase("private")) {
                    client.sendPrivateMessageToServer();
                    continue;
                }
                message = client.getUsername() + ": " + message;
                server.broadcastMessage(message);
            }
        } catch (Exception e) {
            System.out.println ("Error on the client app: " + e.toString());
            System.exit (1);
        }

    }
}
