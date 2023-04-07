import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static User[] getUsers() {
        User user01 = new User("John", "pass");
        User user02 = new User("Robert", "qwerty");
        User user03 = new User("Henry", "123456");
        User user04 = new User("Jayson", "12345");
        User user05 = new User("Andrew", "password");

        User user06 = new User("Annabelle", "123456789");
        User user07 = new User("Mary ", "iloveu");
        User user08 = new User("Helen", "loveme");
        User user09 = new User("Sandra", "123123");
        User user10 = new User("Tatiana", "secret");

        return new User[]{user01, user02, user03, user04, user05,
                user06, user07, user08, user09, user10};
    }

    public static boolean isUserFoundByLogin(String login) {
        User[] users = getUsers();
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUserFoundByLoginAndPassword(String login, String password) {
        User[] users = getUsers();
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Server started.");
        int port = 8089;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in =
                             new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.printf("New connection accepted. Port: %d.%n",
                            clientSocket.getPort());

                    final String login;
                    final String password;
                    String message;

                    out.println("Hi, you are about to enter The Top Secret Server.");

                    out.println("Please, input your login.");
                    login = in.readLine();
                    System.out.println("Login: " + login + ".");

                    if (isUserFoundByLogin(login)) {
                        out.println("Please, input password for login \"" + login + "\".");
                        password = in.readLine();
                        System.out.println("Password: " + password + ".");

                        if (isUserFoundByLoginAndPassword(login, password)) {
                            message = "Access granted.";
                        } else {
                            message = "Wrong password. Access denied.";
                        }
                    } else {
                        message = "Wrong login. Access denied.";
                    }
                    out.println(message);
                    System.out.println(message);
                }
                System.out.println();
            }
        }
    }
}