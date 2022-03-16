package Controller.net;

import Controller.FileController;
import View.GridBagLayoutTest;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Client implements Runnable {
    static Socket socket;

    public Client() {
        try {
            socket = new Socket("localhost", 3345);
            System.out.println("Client connected to socket");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
            GridBagLayoutTest jFrame = new GridBagLayoutTest(dataOutputStream);
            int i = 0;
            while (true) {
                Scanner in = new Scanner(System.in);
                String gettingHash;
                HashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>> hashMap;
                System.out.println("Client initialized");
                //ловим hash с сервера
                gettingHash = Connection.recv(dataInputStream);

                System.out.println("1 for client thread " + Thread.currentThread().getId());
                //тут отображаем во вью то, что пришло
                System.out.println("++++++++++Info from Server+++++++++");
                System.out.println(gettingHash);
                hashMap = FileController.getHashFromString(gettingHash);

                //вынести данную логику на уровень вью!
                //чекаем, что компоненты вью уже инициализированы
                if (i > 0) {
                    jFrame.remove(jFrame.getTable());
                    jFrame.remove(jFrame.getScrollPane());
                    jFrame.remove(jFrame.getAddButton());
                    jFrame.remove(jFrame.getDeleteButton());
                    jFrame.remove(jFrame.getChangeButton());
                }
                jFrame.createPanelUI(hashMap);
                jFrame.pack();
                jFrame.setVisible(true);
                System.out.println("2");
                //это я проверяю, что все изменилось, это не надо во вью
                System.out.println(hashMap);
                i++;
            }
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
