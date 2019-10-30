package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//create the server
public class ServerChatDriver {
    // TODO: 30.10.2019 - check concurrent collection

    public static final String UNIQUE_BINDING_NAME = "server.chat_rmi";

    public static void main(String[] args) throws RemoteException {
        final ServerChat server = new ServerChat();
        try {
            final Registry registry = LocateRegistry.createRegistry(12345);
            registry.bind(UNIQUE_BINDING_NAME, server);
            System.out.println("Server " + UNIQUE_BINDING_NAME + " was started...");

        } catch (Exception e) {
            System.out.println ("Error on the server: " + e.getMessage());
            System.exit (1);
        }
    }

}
