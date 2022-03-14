package Controller.net;

import Controller.FileController;
import Model.Track;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class MonoThreadClientHandler implements Runnable {
    private static Socket clientDialog;

    public MonoThreadClientHandler(Socket client) {
        MonoThreadClientHandler.clientDialog = client;
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
                UUID trackId = UUID.fromString(strId);
                //hashMap.keySet().removeIf(key -> Objects.equals(key, trackId));
                hashMap.remove(strId);
                Track track = FileController.trackFromString(strTrack.toString());
                hashMap.put(trackId, track);
            }
            //remove track
            case 2 -> {
                String strId = splited[1];
                UUID trackId = UUID.fromString(strId);
                hashMap.remove(strId);
                //hashMap.keySet().removeIf(key -> Objects.equals(key, trackId));
            }
            //add new track
            case 3 -> {
                String strId = splited[1];
                String strTrack = splited[2];
                Track newTrack = FileController.trackFromString(strTrack);
                hashMap.put(newTrack.getTrackId(), newTrack);
            }
            //quit
            case 4 -> {

            }

        }
    }

    @Override
    public void run() {
        try (DataOutputStream dataOutputStream = new DataOutputStream(clientDialog.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(clientDialog.getInputStream())) {
            String toCatch;
            //если файл с хэшем не создан, то создать его
            HashMap<UUID, Track> hashMap = FileController.getHashFromFile("hashMap.txt");

            System.out.println("Server initialized");
            Date currDate = new Date();
            while (!clientDialog.isClosed()) {
                if(new Date().getTime() - currDate.getTime() == 60000) {
                    hashMap = FileController.getHashFromFile("hashMap.txt");
                    currDate = new Date();
                }
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
            clientDialog.close();
            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } // TODO Auto-generated catch block
    }
}
