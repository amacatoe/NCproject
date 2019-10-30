package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientChatInterface extends Remote {
    void send(String message) throws RemoteException;
    String getUsername() throws RemoteException;
    void sendPrivateMessageToServer() throws RemoteException;
    void sendMessageToServer(String message) throws RemoteException;
    void registrationOnServer() throws RemoteException;
}
