package client;

import server.ServerChatInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientChat extends UnicastRemoteObject implements ClientChatInterface, Serializable {
    private static final long serialVersionUID;
    private ServerChatInterface serverChatInterface;
    private String username = null;
    static {
        serialVersionUID = 1L;
    }

    protected ClientChat(String username, ServerChatInterface serverChatInterface) throws RemoteException {
        setUsername(username);
        this.serverChatInterface = serverChatInterface;
    }
    public synchronized void send(String message) throws RemoteException {
        System.out.println(message);
    }
    public void setUsername(String username) throws RemoteException {
        this.username = !username.equals("") ? username : "Visitor";
    }
    public synchronized String getUsername() {
        return username;
    }
    public void sendPrivateMessageToServer() throws RemoteException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter recipient name: ");
            String username = br.readLine().trim();
            System.out.print("Enter message: ");
            String message = br.readLine().trim();

            serverChatInterface.sendPrivateMessage(this.username, username, message);

        } catch (IOException e) {
            System.out.println ("Error on the client app: " + e.toString());
            System.exit (1);
        }
    }
}
