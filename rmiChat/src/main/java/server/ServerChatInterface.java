package server;

import client.ClientChatDecorator;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerChatInterface extends Remote {
    void register(ClientChatDecorator clientChat) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
}
