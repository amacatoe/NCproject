package client;

import server.ServerChatInterface;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
        this.serverChatInterface = serverChatInterface;
    }

    //отображаем сообщения на клиенте
    @Override
    public synchronized void send(String message) throws RemoteException {
        System.out.println(message);
    }

    public void setUsername(String username) throws RemoteException {
        this.username = !username.equals("") ? username : "Гость";
    }

    public synchronized String getUsername() {
        return username;
    }
}
