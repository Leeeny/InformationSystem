public class Track {
   private String nameOfTrack;
    private String artist;
    private String album;
    private Long time; //max = 600

        public Track(String nameOfTrack, String artist, String album , Long time) {
        this.nameOfTrack = nameOfTrack;
        this.artist = artist;
        this.album = album;
        this.time = time;

}
    public String getNameOfTrack() {
        return nameOfTrack;
    }

    public void setNameOfTrack(String nameOfTrack) {
        this.nameOfTrack = nameOfTrack;
    }
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Track ['nameOfTrack'][0]['artist'][0]['album'][0]['time'][0]";
