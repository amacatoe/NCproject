package client;


import server.ServerChatInterface;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClientChat extends UnicastRemoteObject implements ClientChatInterface, Runnable, Serializable, Chatable{
    //для Serializable (ук. версии сериал. данных)
    //Нужно ли?
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

    public void setUsername(String username) {
        //придумать проверку, хотя думаю не нужна
        this.username = username;
    }

    @Override
    public String getName() {
        return username;
    }
}
