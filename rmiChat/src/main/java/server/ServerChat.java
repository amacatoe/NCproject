package server;

import client.ClientChatInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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

    public void register(ClientChatInterface clientChat) throws RemoteException {
        chatClients.add(clientChat);
        System.out.println(clientChat.getUsername() + " joined the chat");
        broadcastMessage(clientChat.getUsername() + " joined the chat");
    }

    public void broadcastMessage(String message) {
        for (ClientChatInterface c : chatClients) {
            try {
                c.send(message);
            } catch (RemoteException e) {
                chatClients.remove(c);
            }
        }
    }

    public void sendPrivateMessage(String senderName, String recipientName, String message) {
        for (ClientChatInterface c : chatClients) {
            try {
                if (c.getUsername().equals(recipientName) || c.getUsername().equals(senderName))
                    c.send("[PRIVATE] " + senderName + ": " + message);
            } catch (RemoteException e) {
                chatClients.remove(c);
            }
        }
    }

    public void customLogException(Exception e) {
        System.out.println("Problem in client app: ");
        e.printStackTrace();
    }

    public void deleteClientChat(ClientChatInterface clientChat) {
        try {
            chatClients.remove(clientChat);
            System.out.println(clientChat.getUsername() + " exit from the chat...");
            broadcastMessage(clientChat.getUsername() + " exit from the chat...");
        } catch (Exception e) {
            customLogException(e);
        }
    }

    public boolean checkUsername(String username) {
        if(username.equals("")) return false;
        for(ClientChatInterface c : chatClients) {
            try {
                if(c.getUsername().equals(username)) return false;
            } catch (RemoteException e) {
                chatClients.remove(c);
            }
        }
        return true;
    }
}
