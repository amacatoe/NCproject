package client;


import server.ServerChatInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClientChat extends UnicastRemoteObject implements ClientChatInterface, Runnable, Serializable {
    //для Serializable (ук. версии сериал. данных)
    private static final long serialVersionUID;
    static {
        serialVersionUID = 1L;
    }

    private ServerChatInterface serverChatInterface;
    private String username = null;

    protected ClientChat(String username, ServerChatInterface serverChatInterface) throws RemoteException {
        setUsername(username);
        //подумать про опасности(геттер/сеттер)
        this.serverChatInterface = serverChatInterface;

        //добавляем на сервер новоиспеченный клиент
        serverChatInterface.registerCC(this);
    }

    //отображаем сообщения на клиенте
    public void getMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    //от Runnable, создаем поток, распараллелили
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String message;
        while (true) {
            message = scanner.nextLine();

            try {
                serverChatInterface.broadcastMessage(username + ": " + message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUsername() {
        return new String(username);
    }

    public void setUsername(String username) {
        //придумать проверку
        this.username = username;
    }
}
