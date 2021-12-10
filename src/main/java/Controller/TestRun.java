package Controller;

import Model.Playlist;
import Model.Style;
import Model.Track;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class TestRun {
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

      /*  System.out.println(rockTrack1.toString() + "\nID: " + rockTrack1.getTrackId());
        System.out.println(popTrack.toString() + "\nID: " + popTrack.getTrackId());
        System.out.println(rapTrack.toString() + "\nID: " + rapTrack.getTrackId());
*/
        System.out.println(playlist.getPlaylistId() + " " + playlist.getPlaylistName());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println("song id from playlist " + playlist.getUUID(i));
        }

        System.out.println();
        System.out.println(FileController.TrackToJSON(rockTrack1));


        /*String styleStr = FileController.StyleToJSON(style).toString();
        String trackStr = FileController.TrackToJSON(track).toString();

        FileController.StyleToFile(style, "style.json");
        FileController.TrackToFile(track, "track.json");
        Style style1 = FileController.styleFromFile("style.json");
        Track track1 = FileController.trackFromFile("track.json");
        System.out.println(trackStr);*/

        //to do: toString всего что в модели
        //System.out.println(track.getTrackId());
    }
}
