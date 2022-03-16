package Controller.net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientFabric {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
       for (int i = 0; i < 3; i++) {
            exec.execute(new Client());
            Thread.sleep(10);
        }
        exec.shutdown();
    }
}
