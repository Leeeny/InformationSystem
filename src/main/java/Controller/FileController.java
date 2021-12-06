package Controller;

import Model.Style;
import Model.Track;
import org.json.JSONObject;

public class FileController {

    static String TrackToJSON(Track track) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("track_name", track.getNameOfTrack());
        jsonObject.put("artist", track.getArtist());
        jsonObject.put("album", track.getAlbum());
        jsonObject.put("time", track.getTime().toString());
        jsonObject.put("style_name", track.getNameOfStyle());
        return jsonObject.toString();
    }

    static String StyleToJSON(Style style){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("style_name", style.getNameOfStyle());
        return jsonObject.toString();
    }
}
