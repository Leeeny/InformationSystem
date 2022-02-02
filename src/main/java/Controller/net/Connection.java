package Controller.net;

import Model.Track;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.Collectors;

import static Controller.FileController.trackFromString;

public class Connection {
    public static void send(DataOutputStream dataOutputStream, String str) throws IOException {
        dataOutputStream.writeUTF(str);
        dataOutputStream.flush();
    }

    public static String recv(DataInputStream dataInputStream) throws IOException {
        return dataInputStream.readUTF();
    }

    public static String convert(HashMap<UUID, Track> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> key + ":" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }

    public static HashMap<UUID, Track> convert(String mapAsString) throws ParseException {
        HashMap<UUID, Track> map = null;
        String[] pairs = mapAsString.split(",");
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            map.put(UUID.fromString(keyValue[0]), trackFromString(keyValue[1]));
        }
        return map;
    }
}
