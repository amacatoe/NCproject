package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientChatInterface extends Remote {
    void getMessage(String message) throws RemoteException;
    String getUsername() throws RemoteException;
}
