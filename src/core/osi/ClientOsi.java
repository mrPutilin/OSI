package core.osi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientOsi {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String localhost = "netology.homework";
        int port = 8081;

        try (Socket clientSocket = new Socket(localhost, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out.println("unknown");

            String rest = in.readLine();
            System.out.println(rest);

            String answerName = in.readLine();
            System.out.println(answerName);


            out.println(scanner.nextLine());

            String answerAge = in.readLine();
            System.out.println(answerAge);

            out.println(scanner.nextLine());

            String question = in.readLine();
            System.out.println(question);
        }
    }
}
