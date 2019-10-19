package server;

import client.ClientChatInterface;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerChatInterface extends Remote {
    void register(ClientChatInterface clientChatInterface) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
}
