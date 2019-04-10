/**
 * Created by EIRIK OVESEN on 13.03.2018.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final int PORT_NUMBER = 1337;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Server IP:");
        String serverMachine = scanner.nextLine();

        if (serverMachine.equals("")) {
            serverMachine = "127.0.0.1";
            System.out.println("Connecting to server with IP: " + serverMachine);
        }
        try {
            Socket socket = new Socket(serverMachine, PORT_NUMBER);
            System.out.println("You are now connected to server");

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            String firstLine = bufferedReader.readLine();
            System.out.println(firstLine);

            String userInput = scanner.nextLine();
            while (!userInput.equals("")) {
                printWriter.println(userInput);
                String response = bufferedReader.readLine();
                System.out.println("Result: " + response);
                userInput = scanner.nextLine();
            }

            bufferedReader.close();
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}