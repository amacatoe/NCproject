package client;

import server.ServerChatInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//подключает клиента к серверу
public class ClientChatDriver {
    //Ничего не отправляется

    public static final String UNIQUE_BINDING_NAME = "server.chat_rmi";

    public static void main(String[] args) throws IOException, RemoteException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите Ваше имя: ");
        String username = br.readLine().trim();

        try {
            final Registry registry = LocateRegistry.getRegistry(null, 12345);
            final ServerChatInterface server = (ServerChatInterface) registry.lookup(UNIQUE_BINDING_NAME);
            final ClientChatDecorator client = new ClientChatDecorator(new ClientChat(username, server));

            server.register(client);
        } catch (Exception e) {
            System.out.println ("Ошибка на клиенте: " + e);
            System.exit (1);
        }

    }
}
