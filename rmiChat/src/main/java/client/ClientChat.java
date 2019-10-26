package client;

import server.ServerChatInterface;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClientChat extends UnicastRemoteObject implements ClientChatInterface, Serializable {
    //для Serializable (ук. версии сериал. данных)
    private static final long serialVersionUID;
    static {
        serialVersionUID = 1L;
    }

    private ServerChatInterface serverChatInterface;
    private String username = null;

    protected ClientChat(String username, ServerChatInterface serverChatInterface) throws RemoteException {
        setUsername(username);
        //проверка на null
        this.serverChatInterface = serverChatInterface;
    }

    //отображаем сообщения на клиенте
    public void getMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    public void setUsername(String username) {
        //проверка на пустоту (пробел)
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
