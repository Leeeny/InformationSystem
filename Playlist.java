package Model;


    public class Playlist {

        private Track[] tracks;
        private int count;
        private String playlistName;

        public Playlist() {
            ArrayList<Track> tracks = new ArrayList<Track>();
            tracks.add(track);
            count = 0;
        }

        public String getPlaylistName() {
            return playlistName;
        }

        public void setPlayListName() {
            this.playlistName = playlistName;
        }

        public void add(Track a) {
            if (count == Track.length) {
                System.out.println("ERROR: Collection is full. Songs were not added to the Playlist.");
            }
            tracks[count] = a;
            count++;
        }

        public Track get(int i) {
            if (count > i) {
                return tracks[i];
            } else {
                return null;
            }
        }

        public Track remove(String nameOfTrack) {
            boolean found = false;
            int indexToRemove = 0;
            while (indexToRemove < count && !found) {
                if (tracks[indexToRemove].getNameOfTrack().equals(nameOfTrack)) {
                    found = true;
                } else {
                    indexToRemove++;
                }
            }
            if (indexToRemove < count) {
                for (int from = indexToRemove + 1; from < count; from++) {
                    tracks[from - 1] = tracks[from];
                }
                tracks[count - 1] = null;
                count--;
            }
            return null;
        }

                      
        public void clear() {
            for (int i = 0; i < tracks.length; i++) {
                tracks[i] = null;
                count = 0;
            }
            return;
        }
    }
