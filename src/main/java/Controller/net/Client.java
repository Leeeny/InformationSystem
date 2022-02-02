package Controller.net;

import Controller.FileController;
import Model.Style;
import Model.Track;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

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

    public static String inputValidation(String inp, Scanner scanner) {
        System.out.println(inp);
        String str = null;
        do {
            str = scanner.nextLine();
        } while (Objects.equals(str, "") || Objects.equals(str, null));
        return str;
    }

    //это заменить на функцию из вью
    public static Track getTrackFromConsole() {
        Scanner in = new Scanner(System.in);
        String nameOfTrack = inputValidation("Input nameOfTrack: ", in);
        String artist = inputValidation("Input artist: ", in);
        String album = inputValidation("Input album: ", in);
        System.out.print("Input time: ");
        String s_time = in.nextLine();
        long time = 0;
        try {
            time = Long.parseLong(s_time);
        } catch (NumberFormatException e) {
            System.out.println("Error of number inputting");
            e.printStackTrace();
        }
        System.out.print("Input nameOfStyle: ");
        String nameOfStyle = in.nextLine();
        System.out.printf("nameOfTrack: %s  artist: %s  album: %s time: %s nameOfStyle: %s \n", nameOfTrack, artist, album, time, nameOfStyle);
        return new Track(nameOfTrack, artist, album, time, new Style(nameOfStyle));
    }

    //вот это из вью
    public static int getUserChoice(Scanner in) {
        System.out.println("Do you want to show playlist(0), change(1), delete(2), add new track(3)?");
        String choose = null;
        do {
            choose = in.nextLine();
        } while (!Objects.equals(choose, "0") && !Objects.equals(choose, "1") && !Objects.equals(choose, "2") && !Objects.equals(choose, "3"));
        System.out.println("Your choose " + choose);
        return Integer.parseInt(choose);
    }

    public static String chosenTrack(HashMap<String, Track> hashMap, Scanner in) {
        System.out.println("Please choose track from playlist");
        System.out.println(hashMap.toString());
        String id = ((inputValidation("Enter ID: ", in)));
        return id;
    }

    public static String menu(HashMap<String, Track> hashMap, Scanner in) {
        String trackId = chosenTrack(hashMap, in);
        int choose = getUserChoice(in);
        String toSend = String.valueOf(choose);
        switch (choose) {
            //show playlist
            case 0 -> {
                System.out.println(hashMap.toString());
            }
            //change track
            case 1 -> {
                hashMap.keySet().removeIf(key -> Objects.equals(key, trackId));
                Track track = getTrackFromConsole();
                hashMap.put(trackId, track);
                toSend += "@" + trackId + "@" + FileController.TrackToJSON(track);
            }
            //remove track
            case 2 -> {
                hashMap.keySet().removeIf(key -> Objects.equals(key, trackId));
                toSend += "@" + trackId;
            }
            //add new track
            case 3 -> {
                Track newTrack = getTrackFromConsole();
                hashMap.put(newTrack.getTrackId().toString(), newTrack);
                toSend += "@" + newTrack.getTrackId().toString() + "@" + FileController.TrackToJSON(newTrack);
            }
        }
        return toSend;
    }

    //метод, получающий из вью комманду и возвращающий ее номер (мб удалить или реализовать передачу числа сразу во вью)
    public int getCommandToSend() {
        return 0;
    }

    @Override
    public void run() {
        try (DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
            Scanner in = new Scanner(System.in);
            String gettingHash;
            HashMap<String, Track> hashMap;
            System.out.println("Client initialized");
            //ловим hash с сервера
            gettingHash = Connection.recv(dataInputStream);
            //Thread.sleep(10);

            //тут отображаем во вью то, что пришло
            System.out.println("++++++++++Info from Server+++++++++");
            System.out.println(gettingHash);
            hashMap = FileController.getHashFromString(gettingHash);

            //тут к нам приходят изменения из вью
            String toSend = menu(hashMap, in);

            //это я проверяю, что все изменилось, это не надо во вью
            System.out.println(hashMap);

            //отправляем изменения серверу
            Connection.send(dataOutputStream, toSend);

            Thread.sleep(5000);

        } catch (IOException | InterruptedException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
