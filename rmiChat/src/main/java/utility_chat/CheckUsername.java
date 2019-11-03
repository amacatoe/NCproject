package utility_chat;

import server.ServerChatInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CheckUsername {
    private CheckUsername() {}
    public static String checkUsername(Scanner sc) {
        String username = "";
        try {
            Registry registry = LocateRegistry.getRegistry(null, 12345);
            ServerChatInterface server = (ServerChatInterface)registry.lookup(UtilityClass.UNIQUE_BINDING_NAME);
            System.out.print("Enter your name: ");
            username = sc.nextLine().trim();
            while(!server.checkUsername(username)) {
                System.out.print("Name is empty or already in use. Please choose another one.\nEnter your name: ");
                username = sc.nextLine().trim();
            }
            return username;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }
}
