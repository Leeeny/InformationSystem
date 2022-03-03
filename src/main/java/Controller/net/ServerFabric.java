package Controller.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerFabric {
    static ExecutorService executeIt = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(3345);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Server socket created");
            while (!server.isClosed()) {
                if (bufferedReader.ready()) {
                    System.out.println("Buffer reader is ready");
                    String serverCommand = bufferedReader.readLine();
                    //вот это мб удолить
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        System.out.println("Main Server initiate exiting...");
                        server.close();
                        break;
                    }
                }
                Socket client = server.accept();
                executeIt.execute( new Server(client));
                System.out.print("Connection accepted.");
            }
            executeIt.shutdown();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}