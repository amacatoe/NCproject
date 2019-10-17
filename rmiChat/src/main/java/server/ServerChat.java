package server;

import client.ClientChatInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerChat extends UnicastRemoteObject implements ServerChatInterface {
    private static final long serialVersionUID;

    static {
        serialVersionUID = 1L;
    }

    private List<ClientChatInterface> chatClients;

    protected ServerChat() throws RemoteException {
        chatClients = new ArrayList<ClientChatInterface>();
    }

    //добавляет юзеров чатика
    public synchronized void registerCC(ClientChatInterface clientChatInterface) throws RemoteException {
        this.chatClients.add(clientChatInterface);
    }

    //отлавливает сообщения, показывает юзерам
    public synchronized void broadcastMessage(String message) throws RemoteException {
        int i = 0;

        while(i<chatClients.size()) {
            chatClients.get(i++).getMessage(message);
        }
    }
}
