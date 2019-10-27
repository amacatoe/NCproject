package server;

import client.ClientChatInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

//интерфейс
public interface ServerChatInterface extends Remote {
    void register(ClientChatInterface clientChat) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
}
