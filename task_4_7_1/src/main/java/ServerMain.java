import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
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

                    final String name = in.readLine();
                    out.println(String.format("Hi, %s, your port is %d.",
                            name, clientSocket.getPort()));
                    System.out.println();
                }
            }
        }
    }
}