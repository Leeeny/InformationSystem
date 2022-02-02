package Controller.net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientFabric {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        //newCachedThreadPool

            exec.execute(new Client());
            Thread.sleep(10);

        exec.shutdown();
    }
}
