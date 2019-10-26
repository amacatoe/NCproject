package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//Создает сервер
public class ServerChatDriver {
    //ОТ 26.10
    //посмотреть массивы для rmi в замену методам
    //доделать проверки у сеттеров
    //возможность отправлять приватные сообщения



    //уникальное имя удаленного объекта
    public static final String UNIQUE_BINDING_NAME = "server.chat_rmi";

    public static void main(String[] args) throws RemoteException {
        final ServerChat server = new ServerChat();
        try {
            //реестр удаленных объектов
            final Registry registry = LocateRegistry.createRegistry(12345);
            //регаем заглушку, клиент может найти в реестре удаленных объектов
            registry.bind(UNIQUE_BINDING_NAME, server);
            System.out.println("Сервер " + UNIQUE_BINDING_NAME + " был запущен...");

        } catch (Exception e) {
            System.out.println ("Ошибка на сервере: " + e.getMessage());
            System.exit (1);
        }
    }

}
