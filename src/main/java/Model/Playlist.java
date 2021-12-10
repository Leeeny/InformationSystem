package Model;


import java.util.ArrayList;
import java.util.UUID;

public class Playlist {

    private final UUID playlistId;
    private ArrayList<UUID> idList;
    private String playlistName;

    public Playlist(ArrayList<UUID> idList, String playlistName) {
        this.idList = idList;
        this.playlistName = playlistName;
        playlistId = UUID.randomUUID();
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public ArrayList<UUID> getIdList() {
        return idList;
    }

    public UUID getUUID(int index){
        return idList.get(index);
    }

    public void setIdList(ArrayList<UUID> idList) {
        this.idList = idList;
    }

    public void addTrack(UUID a) {
        idList.add(a);
    }

    public void addTrack(Track a) {
        idList.add(a.getTrackId());
    }

    public UUID getTrackId(int index) {
        return idList.get(index);
    }

    public void remove(UUID id) {
        idList.remove(id);
    }

    public void remove(int index) {
        idList.remove(index);
    }

    public UUID getPlaylistId() {
        return this.playlistId;
    }

    public void clear() {
        idList.clear();
    }

    public int size(){
        return idList.size();
    }
}