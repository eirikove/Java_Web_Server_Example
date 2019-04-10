/**
 * Created by EIRIK OVESEN on 13.03.2018.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class WebServer {
    public static void main(String[] args) {
        final int PORT_NUMBER = 80;

        try {
            ServerSocket socketServer = new ServerSocket(PORT_NUMBER);
            System.out.println("Starting server: ");
            Socket socket = socketServer.accept();

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            ArrayList<String> headerLines = new ArrayList<String>();
            String socketHeaderLines = bufferedReader.readLine();

            while (socketHeaderLines != null) {
                System.out.println("Client wrote: " + socketHeaderLines);

                if (socketHeaderLines.equals("")) {
                    printWriter.println("HTTP/1.1 200 OK");
                    printWriter.println("Content-Type: text/html");
                    printWriter.println();
                    printWriter.println("<html><body>");
                    printWriter.println("<h1>Welcome!</h1>");
                    printWriter.println("Header from client: ");
                    printWriter.println("<ul>");


                    for (int i = 0; i < headerLines.size(); i++) {
                        printWriter.println("<li>" + headerLines.get(i) + "</li>");
                    }

                    printWriter.println("</ul>");
                    printWriter.println("</body></html>");

                    bufferedReader.close();
                    printWriter.close();
                    socket.close();
                    return;
                } else {
                    headerLines.add(socketHeaderLines);
                }
                socketHeaderLines = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}