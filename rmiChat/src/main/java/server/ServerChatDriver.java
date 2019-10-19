package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//Создает сервер
public class ServerChatDriver {
    //уникальное имя удаленного объекта
    public static final String UNIQUE_BINDING_NAME = "server.chat_rmi";

    public static void main(String[] args) throws RemoteException {
        //Подумать как избавиться от Драйвера (а надо ли?)
        //продумать подключение к серверу

        final ServerChat server = new ServerChat();
        try {
            //реестр удаленных объектов
            final Registry registry = LocateRegistry.createRegistry(12345);
            //заглушка, даем возможность делать удаленные вызовы методов сервера
            final ServerChatInterface stub = (ServerChatInterface) UnicastRemoteObject.exportObject(server, 0);
            //регаем заглушку, клиент может найти в реестре удаленных объектов
            registry.bind(UNIQUE_BINDING_NAME, stub);

            //для тестов
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            System.out.println ("Error occured: " + e.getMessage());
            System.exit (1);
        }
    }

}