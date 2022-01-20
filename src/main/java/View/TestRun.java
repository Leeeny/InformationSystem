package View;

import Controller.FileController;
import Model.Playlist;
import Model.Style;
import Model.Track;

import java.io.IOException;
import java.util.*;


public class TestRun {

    public static String inputValidation(String inp, Scanner scanner) {
        System.out.println(inp);
        String str = null;
        do {
            str = scanner.nextLine();
        } while (Objects.equals(str, "") || Objects.equals(str, null));
        return str;
    }

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
        System.out.printf("nameOfTrack: %s  artist: %s  album: %s time: %s nameOfStyle: %s \n", nameOfTrack, artist, album, time, nameOfTrack);
        return new Track(nameOfTrack, artist, album, time, new Style(nameOfStyle));
    }

    public static int getUserChoice(Track track) {
        System.out.println(track.toString());
        System.out.println("Do you want to change(1), delete(2) or add new track(3)?");
        Scanner in = new Scanner(System.in);
        String choose = null;
        do {
            choose = in.nextLine();
        } while (!Objects.equals(choose, "1") && !Objects.equals(choose, "2") && !Objects.equals(choose, "3"));
        System.out.println("Your choose " + choose);
        return Integer.parseInt(choose);
    }

    public static void menu(Track track, Playlist playlist) {
        int choose = getUserChoice(track);
        switch (choose) {
            case 1 -> {
                playlist.remove(track.getTrackId());
                track = getTrackFromConsole();
                playlist.addTrack(track.getTrackId());
            }
            case 2 -> {
                playlist.remove(track.getTrackId());
            }
            case 3 -> {
                Track newTrack = getTrackFromConsole();
                playlist.addTrack(newTrack.getTrackId());
            }
        }
    }

    public static void main(String[] args) {
        Style rock = new Style("Rock");
        Style pop = new Style("Pop");
        Style rap = new Style("Rap");
        Style classic = new Style("classic");

        Track rockTrack1 = new Track("Rape me", "Nirvana", "Nirvana", 180L, rock);
        Track popTrack = new Track("Umbrella", "Rihanna", "Album1", 200L, pop);
        Track rapTrack = new Track("Как дела", "АК-47", "Какой-то альбом", 100L, rap);

        ArrayList<UUID> tracksID = new ArrayList<>();
        tracksID.add(rockTrack1.getTrackId());
        tracksID.add(popTrack.getTrackId());
        tracksID.add(rapTrack.getTrackId());

        Playlist playlist = new Playlist(tracksID, "Очень веселый плейлист");

        HashMap<UUID, Track> tracksIdAndNames = new HashMap<>();
        tracksIdAndNames.put(rockTrack1.getTrackId(), rockTrack1);
        tracksIdAndNames.put(popTrack.getTrackId(), popTrack);
        tracksIdAndNames.put(rapTrack.getTrackId(), rapTrack);

        System.out.println(rockTrack1.toString() + "\nID: " + rockTrack1.getTrackId());
        System.out.println(popTrack.toString() + "\nID: " + popTrack.getTrackId());
        System.out.println(rapTrack.toString() + "\nID: " + rapTrack.getTrackId());

        System.out.println(playlist.getPlaylistId() + " " + playlist.getPlaylistName());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println("song id from playlist " + playlist.getUUID(i));
        }

        System.out.println();
        System.out.println(FileController.TrackToJSON(rockTrack1));


        String styleStr = FileController.StyleToJSON(rock).toString();
        String trackStr = FileController.TrackToJSON(rockTrack1).toString();

        FileController.StyleToFile(pop, "style.json");
        FileController.TrackToFile(popTrack, "track.json");
        Style style1 = FileController.styleFromFile("style.json");
        Track track1 = FileController.trackFromFile("track.json");

        System.out.println(trackStr);

        System.out.println(rockTrack1.getTrackId());


        FileController.saveObjToFile(rapTrack, "saveTrack.txt");
        Track getTrack = FileController.getObjFromFile("saveTrack.txt");
        System.out.println("Deserialized track: ");
        System.out.println(FileController.TrackToJSON(getTrack));

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Track trackFromConsole = getTrackFromConsole();
        playlist.addTrack(trackFromConsole);
        System.out.println(playlist.toString());
        menu(trackFromConsole, playlist);
        System.out.println(playlist.toString());
    }
}