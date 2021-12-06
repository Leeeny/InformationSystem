public class Track {
    private String nameOfTrack;
    private String artist;
    private String album;
    private Long time; //max = 600
    private Style nameOfStyle;

    public Style getStyle() {
        return nameOfStyle;
    }

    public void setStyle(Style nameOfStyle) {
        this.nameOfStyle = nameOfStyle;
    }

    public Track(String nameOfTrack, String artist, String album, Long time) {
        this.nameOfTrack = nameOfTrack;
        this.artist = artist;
        this.album = album;
        this.time = time;

    }

    @Override
    public String toString() {
        return "Track [nameOfTrack=" + nameOfTrack + ", artist=" + artist + ", album=" + album
                + ", time=" + time
                + ", nameOfStyle" + nameOfStyle + "]";
    }
}