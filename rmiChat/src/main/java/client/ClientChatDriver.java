package client;

import java.rmi.RemoteException;
import java.util.Scanner;

//connect the client to the server
public class ClientChatDriver {
    public static void main(String[] args) throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String username = scanner.nextLine().trim();
        final ClientChatInterface client = new ClientChat(username);

        String message = "";
        while (true) {
            message = scanner.nextLine().trim();

            //catch private message in console
            if (message.equalsIgnoreCase("private")) {
                client.sendPrivateMessageToServer();
                continue;
            }
            client.sendMessageToServer(client.getUsername() + ": " + message);
        }

    }
}
