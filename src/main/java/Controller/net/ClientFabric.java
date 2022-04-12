package Controller.net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientFabric {
    public static void main(String[] args) throws InterruptedException {
      /*  Client client;
        for (int i = 0; i < 5; i++) {
            client = new Client();
            System.out.println("1");
            client.start();
        }
        
    }*/
       ExecutorService exec = Executors.newFixedThreadPool(3);
       for (int i = 0; i < 3; i++) {
            exec.submit(new Client());
            Thread.sleep(10);
        }
        exec.shutdown();
    }
}
