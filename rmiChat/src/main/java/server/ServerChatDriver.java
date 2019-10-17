package server;

import client.ClientChatInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerChatDriver {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        //Подумать как избавиться от Драйвера
        //продумать подключение к серверу

        ServerChat server = new ServerChat();
        try {
            ServerChatInterface stub = (ServerChatInterface) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(12345);
            registry.bind("RMIServer", stub);
        } catch (Exception e) {
            System.out.println ("Error occured: " + e.getMessage());
            System.exit (1);
        }
    }

}
