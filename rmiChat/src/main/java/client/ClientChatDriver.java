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

        String command = "";
        while (true) {
            command = scanner.nextLine().trim();

            //catch private message in console
            if (command.equalsIgnoreCase("private")) client.sendPrivateMessageToServer();
            if (command.equalsIgnoreCase("public")) client.sendPublicMessageToServer();
            if (command.equalsIgnoreCase("exit")) client.exit();

        }

    }
}
