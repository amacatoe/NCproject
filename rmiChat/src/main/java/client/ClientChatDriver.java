package client;

import server.ServerChatInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//подключает клиента к серверу
public class ClientChatDriver {
    //Пока что не подключает (java.net.ConnectException: Connection refused: connect)
    //Сервер не создается, решаю проблему с этим


    public static final String UNIQUE_BINDING_NAME = "server.chat_rmi";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите Ваше имя: ");
        String username = br.readLine().trim();

        try {
            final Registry registry = LocateRegistry.getRegistry(null, 12345);
            final ServerChatInterface server = (ServerChatInterface) registry.lookup("ServerChatInterface");
            final ClientChat client = new ClientChat(username, server);

            final ClientChat stub = (ClientChat) UnicastRemoteObject.exportObject(client, 0);
            server.register(stub);
        } catch (Exception e) {
            System.out.println ("Error occured: " + e.getMessage());
            System.exit (1);
        }

    }
}
