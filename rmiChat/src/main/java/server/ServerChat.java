package server;

import client.ClientChatInterface;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServerChat extends UnicastRemoteObject implements ServerChatInterface, Serializable {
    private static final long serialVersionUID;
    private List<ClientChatInterface> chatClients;
    static {
        serialVersionUID = 1L;
    }

    protected ServerChat() throws RemoteException {
        chatClients = new ArrayList<>();
    }
    public synchronized void register(ClientChatInterface clientChat) throws RemoteException {
        this.chatClients.add(clientChat);
        System.out.println(clientChat.getUsername() + " joined the chat");
        broadcastMessage(clientChat.getUsername() + " joined the chat");
    }
    public synchronized void broadcastMessage(String message) throws RemoteException {
        for(ClientChatInterface c : chatClients) {
            c.send(message);
        }
    }
    public synchronized void sendPrivateMessage(String senderName, String recipientName, String message) throws RemoteException {
        for(ClientChatInterface c : chatClients){
            if(c.getUsername().contains(recipientName)) c.send("[PRIVATE] " + senderName + ": " + message);
        }
    }
}
