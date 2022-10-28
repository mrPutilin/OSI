package core.osi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        int port = 8081;
        System.out.println("server started");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    System.out.printf("New connection accepted, Port: %d%n ", clientSocket.getPort());
                    final String unknown = in.readLine();
                    out.println(String.format("Hi %s, your port is %d", unknown, clientSocket.getPort()) + "\n"
                            + "What's your name?");

                    final String answer = in.readLine();
                    System.out.println(answer);

                    out.println("Are you a child?, yes/no");

                    final String answer1 = in.readLine();
                    System.out.println(answer1);

                    if (answer1.equals("yes"))
                        out.println(String.format("Welcome to the kids area, %s ", answer));
                    else out.println(String.format("Welcome to the adult zone, %s, have a good working!", answer));

                }
            }

        }

    }
}
