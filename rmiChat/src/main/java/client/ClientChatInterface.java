package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

//интерфейс
public interface ClientChatInterface extends Remote {
    void send(String message) throws RemoteException;
    String getUsername() throws RemoteException;
}
