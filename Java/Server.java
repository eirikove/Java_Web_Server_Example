/**
 * Created by EIRIK OVESEN on 13.03.2018.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int PORT_NUMBER = 1337;
        Socket socket = null;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
            System.out.println("Starting the server");

            while (true) {
                socket = serverSocket.accept();
                Thread newClientThread = new ThreadManager(socket);
                newClientThread.start();
            }

        } catch(IOException ioe) {

        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}