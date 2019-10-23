package client;

import java.rmi.RemoteException;

//получение какой-то информации о пользователях чата
public class ClientChatDecorator implements ClientChatInterface {
    //Ругается, что сую не Serializable


    private ClientChat client;

    ClientChatDecorator(ClientChat client) {
        this.client = client;
    }

    @Override
    public void getMessage(String message) throws RemoteException {
        client.getMessage(message);
    }

    public String getUsername() {
        return client.getUsername();
    }
}
