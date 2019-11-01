package server;

import client.ClientChatInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerChatInterface extends Remote {
    void register(ClientChatInterface clientChat) throws RemoteException;
    void broadcastMessage(String message) throws RemoteException;
    void sendPrivateMessage(String senderName, String recipientName, String message) throws RemoteException;
    void customLogException(Exception e) throws RemoteException;
}
