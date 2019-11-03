package client;

import utility_chat.CheckUsername;

import java.rmi.RemoteException;
import java.util.Scanner;

//connect the client to the server
public class ClientChatDriver {
    public static void main(String[] args) throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        final ClientChatInterface client = new ClientChat(CheckUsername.checkUsername(scanner));

        String command;
        while (true) {
            command = scanner.nextLine().trim();
            if (command.equalsIgnoreCase("private")) client.sendPrivateMessageToServer();
            if (command.equalsIgnoreCase("public")) client.sendPublicMessageToServer();
            if (command.equalsIgnoreCase("exit")) client.exit();
        }
    }
}
