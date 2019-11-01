package server;

import utility_chat.UtilityClass;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//create the server
public class ServerChatDriver {
    // TODO: 31.10.2019 - ?

    public static void main(String[] args) throws RemoteException {
        final ServerChat server = new ServerChat();
        try {
            final Registry registry = LocateRegistry.createRegistry(12345);
            registry.bind(UtilityClass.UNIQUE_BINDING_NAME, server);
            System.out.println("Server " + UtilityClass.UNIQUE_BINDING_NAME + " was started...");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit (1);
        }
    }

}
