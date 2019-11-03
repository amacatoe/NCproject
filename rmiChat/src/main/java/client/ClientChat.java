package client;

import server.ServerChatInterface;
import utility_chat.UtilityClass;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClientChat extends UnicastRemoteObject implements ClientChatInterface, Serializable {
    private static final long serialVersionUID;
    private ServerChatInterface serverChatInterface;
    private String username = null;

    static {
        serialVersionUID = 1L;
    }

    ClientChat(String username) throws RemoteException {
        setUsername(username);
        registrationOnServer();
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void send(String message) {
        System.out.println(message);
    }

    public void sendPrivateMessageToServer() throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter recipient name: ");
        String username = sc.nextLine().trim();
        System.out.print("Enter message: ");
        String message = sc.nextLine().trim();

        serverChatInterface.sendPrivateMessage(this.username, username, message);
    }

    public void sendPublicMessageToServer() throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message: ");
        String message = sc.nextLine().trim();
        serverChatInterface.broadcastMessage(getUsername() + ": " + message);
    }

    public void registrationOnServer() throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(null, 12345);
            serverChatInterface = (ServerChatInterface) registry.lookup(UtilityClass.UNIQUE_BINDING_NAME);
            serverChatInterface.register(this);
            serverChatInterface.sendPrivateMessage("Server", getUsername(), "You can send private message, please enter the phrase 'private'");
            serverChatInterface.sendPrivateMessage("Server", getUsername(), "You can send public message, please enter the phrase 'public'");
            serverChatInterface.sendPrivateMessage("Server", getUsername(), "You can exit from the chat, please enter the phrase 'exit'");
        } catch (Exception e) {
            serverChatInterface.customLogException(e);
        }
    }

    public void exit() throws RemoteException {
        serverChatInterface.deleteClientChat(this);
        System.exit(1);
    }

    @Override
    public String toString() {
        return "Chat user {" + "username: " + username + '}';
    }
}
