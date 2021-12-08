package Model;

public class Driver {

    public static void main(String[] args) {
        Playlist one = new Playlist();

        Track track1 = new Song("name1", "artist1", "album1", 300, "style1");
        Track track2 = new Song("name2", "artist2", "album2", 310, "style2");
        Track track3 = new Song("name3", "artist3", "album3", 320, "style3");
        Track track4 = new Song("name4", "artist4", "album4", 330, "style4");
        Track track5 = new Song("name5", "artist5", "album5", 340, "style5");
        

        one.add(track1);
        one.add(track2);
        one.add(track3);
        one.add(track4);
        one.add(track5);
        

        one.print();

        one.remove("name4");
        one.remove("name5");

        one.print();
        one.clear();
    }

    