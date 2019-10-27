package server;

import client.ClientChatInterface;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerChat extends UnicastRemoteObject implements ServerChatInterface, Serializable {
    //для Serializable (ук. версии сериал. данных)
    private static final long serialVersionUID;
    private List<ClientChatInterface> chatClients;

    static {
        serialVersionUID = 1L;
    }

    protected ServerChat() throws RemoteException {
        chatClients = new ArrayList<>();
    }

    //добавляет юзеров чата
    public synchronized void register(ClientChatInterface clientChat) throws RemoteException {
        this.chatClients.add(clientChat);
        System.out.println(clientChat.getUsername() + " присоединился к чату");
        broadcastMessage(clientChat.getUsername() + " присоединился к чату");
    }

    //отлавливает сообщения, передаем юзерам
    public synchronized void broadcastMessage(String message) throws RemoteException {
        for(ClientChatInterface c : chatClients) {
            c.send(message);
        }
    }
}
