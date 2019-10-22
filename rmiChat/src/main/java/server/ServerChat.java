package server;

import client.ClientChatInterface;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerChat extends UnicastRemoteObject implements ServerChatInterface, Serializable {
    //для Serializable (ук. версии сериал. данных)
    //Нужно ли?
    private static final long serialVersionUID;

    static {
        serialVersionUID = 1L;
    }

    private List<ClientChatInterface> chatClients;

    protected ServerChat() throws RemoteException {
        chatClients = new ArrayList<>();
    }

    //добавляет юзеров чатика
    public synchronized void register(ClientChatInterface clientChatInterface) throws RemoteException {

        //сообразить с обертками
        //System.out.println(clientChatInterface.getName() + " присоединился к чату");
        this.chatClients.add(clientChatInterface);
    }

    //отлавливает сообщения, передаем юзерам
    public synchronized void broadcastMessage(String message) throws RemoteException {
        int i = 0;

        while(i<chatClients.size()) {
            chatClients.get(i++).getMessage(message);
        }
    }
}
