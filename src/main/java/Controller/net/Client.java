package Controller.net;

import Controller.FileController;
import View.GridBagLayoutTest;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Client extends Thread {
    static Socket socket;

    public Client() {
        try {
            socket = new Socket("localhost", 3345);
            out.println("Client connected to socket");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Socket socket) throws IOException {
        try {
            out.close();
        }
        finally {
            try {
                in.close();
            }
            finally {
                socket.close();
            }
        }
    }

    @Override
    public void run() {
        try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
            boolean flag = true;
            GridBagLayoutTest jFrame = new GridBagLayoutTest(dataOutputStream, dataInputStream, socket, flag);
            int i = 0;
            while (jFrame.getFlag()) {
                String gettingHash;
                HashMap<String, LinkedHashMap<String, LinkedHashMap<String, Object>>> hashMap;
                out.println("Client initialized");

                //ловим hash с сервера
                gettingHash = Connection.recv(dataInputStream);
                out.println("1 for client thread " + Thread.currentThread().getId());
                //тут отображаем во вью то, что пришло
                out.println("++++++++++Info from Server+++++++++");
                out.println(gettingHash);
                hashMap = FileController.getHashFromString(gettingHash);

                //чекаем, что компоненты вью уже инициализированы
                if (i > 0) {
                    jFrame.update();
                }
                jFrame.createPanelUI(hashMap);
                jFrame.pack();
                jFrame.setVisible(true);
                out.println("2");
                //это я проверяю, что все изменилось, это не надо во вью
                out.println(hashMap);
                i++;
            }
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
