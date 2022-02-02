package Controller.net;

import Controller.FileController;
import Model.Track;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class Server implements Runnable {
    private static Socket clientDialog;

    public Server(Socket client) {
        Server.clientDialog = client;
    }

    private void parceEntery(String catched, HashMap<UUID, Track> hashMap) throws ParseException {

        String[] splited = catched.split("@");
        int choose = Integer.parseInt(splited[0]);
        switch (choose) {
            //show playlist
            case 0 -> {
                System.out.println(hashMap.toString());
            }
            //change track
            case 1 -> {
                String strId = splited[1];
                String strTrack = splited[2];
                UUID trackId = UUID.fromString(strId.toString());
                hashMap.keySet().removeIf(key -> Objects.equals(key, trackId));
                Track track = FileController.trackFromString(strTrack.toString());
                hashMap.put(trackId, track);
            }
            //remove track
            case 2 -> {
                String strId = splited[1];
                UUID trackId = UUID.fromString(strId);
                hashMap.keySet().removeIf(key -> Objects.equals(key, trackId));
            }
            //add new track
            case 3 -> {
                String strId = splited[1];
                String strTrack = splited[2];
                Track newTrack = FileController.trackFromString(strTrack);
                hashMap.put(newTrack.getTrackId(), newTrack);
            }
        }
    }

    @Override
    public void run() {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(clientDialog.getInputStream());
            String toCatch;
            HashMap<UUID, Track> hashMap = FileController.getHashFromFile("hashMap.txt");

            System.out.println("Server initialized");

            while (!clientDialog.isClosed()) {

                //отправляем на клиент данные с сервера
                Connection.send(dataOutputStream, FileController.getStrFromHash(hashMap));
                System.out.println("Server send hashMap");

                //получаем ответ
                toCatch = Connection.recv(dataInputStream);
                System.out.println(toCatch);

                //парсим и меняем hashMap
                parceEntery(toCatch, hashMap);
                System.out.println(hashMap);
                FileController.hashToFile(hashMap, "hashMap.txt");

            }
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");
            dataInputStream.close();
            dataOutputStream.close();
            clientDialog.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } // TODO Auto-generated catch block

    }
}
