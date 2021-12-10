package Model;


import java.util.ArrayList;

public class Playlist {

    private ArrayList<Track> tracks;
    private String playlistName;

    public Playlist(ArrayList<Track> tracks, String playlistName) {
        this.tracks = tracks;
        this.playlistName = playlistName;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks){
        this.tracks = tracks;
    }

    public void addTrack(Track a) {
        tracks.add(a);
    }

    public Track getTrack(int index) {
        return tracks.get(index);
    }

    public void remove(String nameOfTrack) {
        boolean found = false;
        int indexToRemove = 0;
        while (indexToRemove < tracks.size() && !found) {
            if (tracks.get(indexToRemove).getNameOfTrack().equals(nameOfTrack)) {
                found = true;
            } else {
                indexToRemove++;
            }
        }
        if (indexToRemove < tracks.size()) {
            for (int from = indexToRemove + 1; from < tracks.size(); from++) {
                tracks.set(from - 1, tracks.get(from));
            }
            /*tracks[count - 1] = null;
            count--;*/
        }
    }


    public void clear() {
        for (int i = 0; i < tracks.size(); i++) {
           tracks.clear(); //оно так работает??
        }
    }
}