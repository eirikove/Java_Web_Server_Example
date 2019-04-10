/**
 * Created by EIRIK OVESEN on 13.03.2018.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ThreadManager extends Thread {

    private Socket socket;

    public ThreadManager(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            printWriter.println("Server connection ");

            String userInput = bufferedReader.readLine();
            while (userInput != null) {
                System.out.println("Client wrote: " + userInput);

                int sumNumbers = -1;
                if (userInput.contains("+")) {
                    sumNumbers = Integer.parseInt(userInput.split("\\+")[0]) + Integer.parseInt(userInput.split("\\+")[1]);
                } else if (userInput.contains("-")) {
                    sumNumbers = Integer.parseInt(userInput.split("-")[0]) - Integer.parseInt(userInput.split("-")[1]);
                }

                if (sumNumbers == -1){
                    printWriter.println("Please write an arithmetic calculation. Example: 1+1 or 90-4");
                }else {
                    printWriter.println("= " + sumNumbers);
                }

                userInput = bufferedReader.readLine();
            }

            bufferedReader.close();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}