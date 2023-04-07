import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8089;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in =
                     new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            // Initial instruction
            String resp;
            resp = in.readLine();
            System.out.println(resp);
            resp = in.readLine();
            System.out.println(resp);

            // Login input
            String login = scanner.nextLine();
            out.println(login);

            // Password input
            resp = in.readLine();
            System.out.println(resp);
            if (!resp.contains("Wrong")) {
                String password = scanner.nextLine();
                out.println(password);

                // Final access response
                resp = in.readLine();
                System.out.println(resp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}