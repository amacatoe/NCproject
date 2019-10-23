package server;

import client.ClientChatDecorator;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerChat extends UnicastRemoteObject implements ServerChatInterface, Serializable {
    //для Serializable (ук. версии сериал. данных)
    private static final long serialVersionUID;

    static {
        serialVersionUID = 1L;
    }
    private List<ClientChatDecorator> chatClients;

    protected ServerChat() throws RemoteException {
        chatClients = new ArrayList<>();
    }

    //добавляет юзеров чатика
    public synchronized void register(ClientChatDecorator clientChat) throws RemoteException {
        System.out.println(clientChat.getUsername() + " присоединился к чату");
        this.chatClients.add(clientChat);
    }

    //отлавливает сообщения, передаем юзерам
    public synchronized void broadcastMessage(String message) throws RemoteException {
        for(ClientChatDecorator c : chatClients) {
            c.getMessage(message);
        }

//        int i = 0;
//        while(i<chatClients.size()) {
//            chatClients.get(i++).getMessage(message);
//        }
    }
}
