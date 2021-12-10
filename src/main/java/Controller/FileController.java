package Controller;

import Model.Style;
import Model.Track;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileController {

    static JSONObject TrackToJSON(Track track) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("track_name", track.getNameOfTrack()); //putString ?
        jsonObject.put("artist", track.getArtist());
        jsonObject.put("album", track.getAlbum());
        jsonObject.put("time", track.getTime());
        jsonObject.put("style_name", track.getNameOfStyle().getNameOfStyle()); //гениальная конструкция
        return jsonObject;
    }

    static JSONObject StyleToJSON(Style style) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("style_name", style.getNameOfStyle());
        return jsonObject;
    }

    static void TrackToFile(Track track, String filename) {
        JSONObject jsonObject = TrackToJSON(track);
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void StyleToFile(Style style, String filename) {
        JSONObject jsonObject = StyleToJSON(style);
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject FileToJSON(String filename) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try (FileReader fileReader = new FileReader(filename)) {
            jsonObject = (JSONObject) jsonParser.parse(fileReader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static Track trackFromFile(String filename) {
        JSONObject jsonObject = FileToJSON(filename);
        String trackName = (String) jsonObject.get("track_name");
        String artist = (String) jsonObject.get("artist");
        String album = (String) jsonObject.get("album");
        Long time = (Long) jsonObject.get("time");
        String str = (String) jsonObject.get("style_name");
        Style style = new Style(str);
        return new Track(trackName, artist, album, time, style);
    }

    public static Style styleFromFile(String filename) {
        JSONObject jsonObject1 = FileToJSON(filename);
        String styleName = (String) jsonObject1.get("style_name");
        return new Style(styleName);
    }


}
