package client;


import server.ServerChatInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientChat extends UnicastRemoteObject implements ClientChatInterface {

}
