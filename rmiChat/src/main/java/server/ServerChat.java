package server;

import client.ClientChatInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerChat extends UnicastRemoteObject implements ServerChatInterface, Serializable {
    private static final long serialVersionUID;
    private List<ClientChatInterface> chatClients;

    static {
        serialVersionUID = 1L;
    }

    ServerChat() throws RemoteException {
        chatClients = new CopyOnWriteArrayList<>();
    }

    public synchronized void register(ClientChatInterface clientChat) throws RemoteException {
        chatClients.add(clientChat);
        System.out.println(clientChat.getUsername() + " joined the chat");
        broadcastMessage(clientChat.getUsername() + " joined the chat");
    }

    public synchronized void broadcastMessage(String message) throws RemoteException {
        for (ClientChatInterface c : chatClients) {
            c.send(message);
        }
    }

    public synchronized void sendPrivateMessage(String senderName, String recipientName, String message) throws RemoteException {
        Iterator<ClientChatInterface> iterator = chatClients.iterator();

        for (ClientChatInterface c : chatClients) {
            if (c.getUsername().equals(recipientName)) c.send("[PRIVATE] " + senderName + ": " + message);
        }
    }

    public synchronized void customLogException(Exception e) throws RemoteException {
        System.out.println("Problem in client app: ");
        e.printStackTrace();
    }

    @Override
    public void deleteClientChat(ClientChatInterface clientChat) throws RemoteException {
        System.out.println(clientChat.getUsername() + " exit from the chat...");
        broadcastMessage(clientChat.getUsername() + " exit from the chat...");
        chatClients.remove(clientChat);
    }
}
